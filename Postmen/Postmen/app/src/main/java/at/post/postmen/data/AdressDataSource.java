package at.post.postmen.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 04.07.2015.
 */
public class AdressDataSource {

    private SQLiteDatabase db;
    private PostmenDbHelper dbHelper;
    private String[] allColumns = {"id", "street", "number", "parcels"};

    public AdressDataSource(Context context) {
        dbHelper = new PostmenDbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Adress createAdress(String street, String number, int parcels) {
        ContentValues values = new ContentValues();
        values.put("street", street);
        values.put("number", number);
        values.put("parcels", parcels);

        long insertId = db.insert("Adresses", null, values);

        Cursor cursor = db.query("Adresses", allColumns, "id = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        return cursorToAdress(cursor);
    }

    public Adress createAdress(String street, String number) {
        ContentValues values = new ContentValues();
        values.put("street", street);
        values.put("number", number);
        values.put("parcels", 0);

        long insertId = db.insert("Adresses", null, values);

        Cursor cursor = db.query("Adresses", allColumns, "id = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        return cursorToAdress(cursor);
    }

    public List<Adress> getAllAdresses() {
        List<Adress> AdressList = new ArrayList<Adress>();

        Cursor cursor = db.query("Adresses", allColumns, null, null, null, null,null);
        cursor.moveToFirst();

        if(cursor.getCount() == 0) return AdressList;

        while (cursor.isAfterLast() == false)
        {
            Adress newAdress= cursorToAdress(cursor);
            AdressList.add(newAdress);
            cursor.moveToNext();
        }

        cursor.close();
        return AdressList;
    }

    public List<String> getStreets() {
        List<String> streetsList = new ArrayList<String>();
        Cursor cursor = db.query(true,
                dbHelper.TABLE_ADRESSES,
                new String[] {dbHelper.COLUMN_STREET},
                null, null,
                dbHelper.COLUMN_STREET, null, null, null);
        cursor.moveToFirst();

        if(cursor.getCount() == 0) return streetsList;

        Log.d("AdressDataSource", "Cursorgroeße " + Integer.toString(cursor.getCount()));
        Log.d("AdressDataSource", "Columnsize of Cursor " + Integer.toString(cursor.getColumnCount()));

        while(cursor.isAfterLast() == false)
        {
            String newStreet = cursor.getString(0);
            streetsList.add(newStreet);
            cursor.moveToNext();
        }
        cursor.close();

        return streetsList;
    }

    public List<String> getNumbersOfStreet(String street) {
        List<String> numberList = new ArrayList<String>();
        Cursor cursor = db.query(dbHelper.TABLE_ADRESSES,
                new String[] {dbHelper.COLUMN_NUMBER},
                dbHelper.COLUMN_STREET + "=?", new String[]{street},
                null, null, dbHelper.COLUMN_NUMBER + " ASC");
        cursor.moveToFirst();

        if(cursor.getCount() == 0) return numberList;

        while(cursor.isAfterLast() == false)
        {
            String newNumber = cursor.getString(0);
            numberList.add(newNumber);
            cursor.moveToNext();
        }
        cursor.close();

        return numberList;
    }

    public Adress getOneAdress(String street, String number){
        Adress oneAdress = new Adress();
        try {
            this.open();
            String selection = dbHelper.COLUMN_STREET + " = ? AND " + dbHelper.COLUMN_NUMBER + " = ?";
            Cursor cursor = db.query(true, dbHelper.TABLE_ADRESSES, allColumns, selection, new String[] {street, number}, null, null, null, null);
            cursor.moveToFirst();
            this.close();
            oneAdress = cursorToAdress(cursor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oneAdress;
    }

    public List<Adress> getRemainingParcels(){
        List<Adress> adressList = new ArrayList<Adress>();
        try {
            this.open();
            String selection = dbHelper.COLUMN_PARCELS + " > ? ";
            Cursor cursor = db.query(true, dbHelper.TABLE_ADRESSES, allColumns, selection, new String[] {"0"}, null, null, null, null);
            cursor.moveToFirst();
            this.close();

            if(cursor.getCount() == 0) return adressList;

            while (cursor.isAfterLast() == false)
            {
                Adress newAdress= cursorToAdress(cursor);
                adressList.add(newAdress);
                cursor.moveToNext();
            }

            cursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adressList;
    }

    private Adress cursorToAdress(Cursor cursor) {
        Adress adress = new Adress();
        adress.setId(cursor.getLong(0));
        adress.setStreet(cursor.getString(1));
        adress.setNumber(cursor.getString(2));
        adress.setParcels(cursor.getInt(3));
        return adress;
    }

    public void resetParcels() {
        try {
            this.open();
            ContentValues values = new ContentValues();
            values.put(dbHelper.COLUMN_PARCELS, 0);
            db.update(dbHelper.TABLE_ADRESSES, values, null, null);
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void resetDb() {
        try {
            this.open();
            db.execSQL("DROP TABLE IF EXISTS ADRESSES");
            db.execSQL(dbHelper.SQL_CREATE);
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateParcelNumber(Adress adress ) {
        try {
            this.open();
            ContentValues values = new ContentValues();
            values.put(dbHelper.COLUMN_PARCELS, adress.getParcels() + 1);
            String selection = dbHelper.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { Long.toString(adress.getId()) };

            db.update(dbHelper.TABLE_ADRESSES, values, selection, selectionArgs);
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateParcelNumber(Adress adress, Boolean toNull ) {
        try {
            this.open();
            ContentValues values = new ContentValues();
            if(toNull) {
                values.put(dbHelper.COLUMN_PARCELS, 0);
            } else{
                values.put(dbHelper.COLUMN_PARCELS, adress.getParcels() + 1);
            }
            String selection = dbHelper.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { Long.toString(adress.getId()) };

            db.update(dbHelper.TABLE_ADRESSES, values, selection, selectionArgs );
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
