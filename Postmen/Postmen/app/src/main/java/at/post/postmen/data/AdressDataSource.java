package at.post.postmen.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private String[] allColumns = {dbHelper.COLUMN_ID, dbHelper.COLUMN_STREET, dbHelper.COLUMN_NUMBER, dbHelper.COLUMN_PARCELS, dbHelper.COLUMN_MONEY};

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
        values.put(dbHelper.COLUMN_STREET, street);
        values.put(dbHelper.COLUMN_NUMBER, number);
        values.put(dbHelper.COLUMN_PARCELS, parcels);
        values.put(dbHelper.COLUMN_MONEY, 0);

        long insertId = db.insert(dbHelper.TABLE_ADRESSES, null, values);

        Cursor cursor = db.query(dbHelper.TABLE_ADRESSES, allColumns, dbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        return cursorToAdress(cursor);
    }

    public Adress createAdress(String street, String number) {
        Cursor cursor = db.rawQuery("SELECT " + PostmenDbHelper.COLUMN_ID +", " +
                PostmenDbHelper.COLUMN_STREET + ", " +
                PostmenDbHelper.COLUMN_NUMBER + ", " +
                PostmenDbHelper.COLUMN_PARCELS + ", " +
                PostmenDbHelper.COLUMN_MONEY + " " +
                " FROM " + PostmenDbHelper.TABLE_ADRESSES +
                " WHERE " + PostmenDbHelper.COLUMN_STREET + " = ? AND " +
                PostmenDbHelper.COLUMN_NUMBER + " = ?", new String[]{street, number});
                //db.query("Adresses", allColumns, PostmenDbHelper.COLUMN_STREET + " = " + street + " AND " + PostmenDbHelper.COLUMN_NUMBER + " = " + number, null, null, null, null);

        if(cursor.getCount() <= 0)
        {
            ContentValues values = new ContentValues();
            values.put(PostmenDbHelper.COLUMN_STREET, street);
            values.put(PostmenDbHelper.COLUMN_NUMBER, number);
            values.put(PostmenDbHelper.COLUMN_PARCELS, 0);
            values.put(PostmenDbHelper.COLUMN_MONEY, 0);

            long insertId = db.insert(PostmenDbHelper.TABLE_ADRESSES, null, values);

            cursor = db.query(PostmenDbHelper.TABLE_ADRESSES, allColumns, PostmenDbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
            cursor.moveToFirst();

            Adress adress = cursorToAdress(cursor);
            cursor.close();
            return adress;
        }
        cursor.moveToFirst();
        Adress adress = cursorToAdress(cursor);
        cursor.close();
        return adress;

    }

    public List<Adress> getAllAdresses() {
        List<Adress> AdressList = new ArrayList<Adress>();

        Cursor cursor = db.query(PostmenDbHelper.TABLE_ADRESSES, allColumns, null, null, null, null,null);
        cursor.moveToFirst();

        if(cursor.getCount() == 0)
        {
            cursor.close();
            return AdressList;
        }

        while (!cursor.isAfterLast())
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
                PostmenDbHelper.TABLE_ADRESSES,
                new String[] {PostmenDbHelper.COLUMN_STREET},
                null, null,
                PostmenDbHelper.COLUMN_STREET, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0){
            cursor.close();
            return streetsList;
        }

        Log.d("AdressDataSource", "Cursorgroeße " + Integer.toString(cursor.getCount()));
        Log.d("AdressDataSource", "Columnsize of Cursor " + Integer.toString(cursor.getColumnCount()));

        while(!cursor.isAfterLast())
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
        Cursor cursor = db.query(PostmenDbHelper.TABLE_ADRESSES,
                new String[] {PostmenDbHelper.COLUMN_NUMBER},
                PostmenDbHelper.COLUMN_STREET + "=?", new String[]{street},
                null, null, PostmenDbHelper.COLUMN_NUMBER + " ASC");
        cursor.moveToFirst();

        if(cursor.getCount() == 0){
            cursor.close();
            return numberList;
        }

        while(!cursor.isAfterLast())
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
            String selection = PostmenDbHelper.COLUMN_STREET + " = ? AND " + PostmenDbHelper.COLUMN_NUMBER + " = ?";
            Cursor cursor = db.query(true, PostmenDbHelper.TABLE_ADRESSES, allColumns, selection, new String[] {street, number}, null, null, null, null);
            cursor.moveToFirst();
            this.close();
            oneAdress = cursorToAdress(cursor);
            cursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oneAdress;
    }

    public List<Adress> getRemainingParcels(){
        List<Adress> adressList = new ArrayList<Adress>();
        try {
            this.open();
            String selection = PostmenDbHelper.COLUMN_PARCELS + " > ? OR " + PostmenDbHelper.COLUMN_MONEY + " > ? ";
            Cursor cursor = db.query(true, PostmenDbHelper.TABLE_ADRESSES, allColumns, selection, new String[] {"0", "0"}, null, null, null, null);
            cursor.moveToFirst();
            this.close();

            if(cursor.getCount() == 0){
                cursor.close();
                return adressList;
            }

            while (!cursor.isAfterLast())
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

    public Adress cursorToAdress(Cursor cursor) {
        Adress adress = new Adress();
        adress.setId(cursor.getLong(0));
        adress.setStreet(cursor.getString(1));
        adress.setNumber(cursor.getString(2));
        adress.setParcels(cursor.getInt(3));
        adress.setMoney(cursor.getInt(4));
        return adress;
    }

    public void resetParcels() {
        try {
            this.open();
            ContentValues values = new ContentValues();
            values.put(PostmenDbHelper.COLUMN_PARCELS, 0);
            values.put(PostmenDbHelper.COLUMN_MONEY, 0);
            db.update(PostmenDbHelper.TABLE_ADRESSES, values, null, null);
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void resetDb() {
        try {
            this.open();
            db.execSQL("DROP TABLE IF EXISTS " + PostmenDbHelper.TABLE_ADRESSES);
            db.execSQL(PostmenDbHelper.SQL_CREATE_TABLE_ADRESSES);
            db.execSQL("DROP TABLE IF EXISTS " + dbHelper.TABLE_SIGRELAUT);
            db.execSQL(PostmenDbHelper.SQL_CREATE_TABLE_SIGRELAUT);
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateParcelNumber(Adress adress ) {
        try {
            this.open();
            ContentValues values = new ContentValues();
            values.put(PostmenDbHelper.COLUMN_PARCELS, adress.getParcels() + 1);
            String selection = PostmenDbHelper.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { Long.toString(adress.getId()) };

            db.update(PostmenDbHelper.TABLE_ADRESSES, values, selection, selectionArgs);
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
                values.put(PostmenDbHelper.COLUMN_PARCELS, 0);
            } else{
                values.put(PostmenDbHelper.COLUMN_PARCELS, adress.getParcels() + 1);
            }
            String selection = PostmenDbHelper.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { Long.toString(adress.getId()) };

            db.update(PostmenDbHelper.TABLE_ADRESSES, values, selection, selectionArgs);
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMoneyNumber(Adress adress ) {
        try {
            this.open();
            ContentValues values = new ContentValues();
            values.put(PostmenDbHelper.COLUMN_MONEY, adress.getMoney() + 1);
            String selection = PostmenDbHelper.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { Long.toString(adress.getId()) };

            db.update(PostmenDbHelper.TABLE_ADRESSES, values, selection, selectionArgs);
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMoneyNumber(Adress adress, Boolean toNull ) {
        try {
            this.open();
            ContentValues values = new ContentValues();
            if(toNull) {
                values.put(PostmenDbHelper.COLUMN_MONEY, 0);
            } else{
                values.put(PostmenDbHelper.COLUMN_MONEY, adress.getMoney() + 1);
            }
            String selection = PostmenDbHelper.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { Long.toString(adress.getId()) };

            db.update(PostmenDbHelper.TABLE_ADRESSES, values, selection, selectionArgs );
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
