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

    public void updateParcelNumber(Adress adress) {
        try {
            this.open();
            ContentValues values = new ContentValues();
            values.put(dbHelper.COLUMN_PARCELS, adress.getParcels() + 1);
            String selection = dbHelper.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { Long.toString(adress.getId()) };

            db.update(dbHelper.TABLE_ADRESSES, values, selection, selectionArgs );
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
