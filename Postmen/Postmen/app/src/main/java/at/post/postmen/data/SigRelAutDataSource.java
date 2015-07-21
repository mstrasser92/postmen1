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
 * Created by Michael on 14.07.2015.
 */
public class SigRelAutDataSource {


    private SQLiteDatabase db;
    private PostmenDbHelper dbHelper;
    private String[] allColumns = {dbHelper.COLUMN_ID, dbHelper.COLUMN_ADRESS_ID, dbHelper.COLUMN_NAME};

    public SigRelAutDataSource(Context context) {
        dbHelper = new PostmenDbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public SignatureReleaseAuthorisation createSigRelAut(Adress adress, String name){
        SignatureReleaseAuthorisation sigRelAut = new SignatureReleaseAuthorisation();
        sigRelAut.setAdress(adress);
        sigRelAut.setName(name);
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_ADRESS_ID, String.valueOf(adress.getId()));
        values.put(dbHelper.COLUMN_NAME, name);

        long insertId = db.insert(dbHelper.TABLE_SIGRELAUT, null, values);

        sigRelAut.setId(insertId);

        return  sigRelAut;
    }

    public SignatureReleaseAuthorisation createSigRelAut(String street, String number, String name){
        Cursor cursor = db.query(PostmenDbHelper.TABLE_ADRESSES,
                new String[]{dbHelper.COLUMN_ID, dbHelper.COLUMN_STREET, dbHelper.COLUMN_NUMBER,dbHelper.COLUMN_PARCELS},
                dbHelper.COLUMN_STREET + " = ? AND " + dbHelper.COLUMN_NUMBER + " = ? ",
                new String[]{street, number},
                null, null, null, null);
        cursor.moveToFirst();
        Adress adress = cursorToAdress(cursor);

        SignatureReleaseAuthorisation sigRelAut = new SignatureReleaseAuthorisation();
        sigRelAut.setAdress(adress);
        sigRelAut.setName(name);
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_ADRESS_ID, String.valueOf(adress.getId()));
        values.put(dbHelper.COLUMN_NAME, name);

        long insertId = db.insert(PostmenDbHelper.TABLE_SIGRELAUT, null, values);

        sigRelAut.setId(insertId);
        return null;
    }

    public SignatureReleaseAuthorisation cursorToSigRelAut(Cursor cursor){
        SignatureReleaseAuthorisation sigRelAut = new SignatureReleaseAuthorisation();
        sigRelAut.setId(cursor.getLong(0));
        Adress adress = cursorToAdress(db.query(true, PostmenDbHelper.TABLE_ADRESSES, allColumns, dbHelper.COLUMN_ID + " = ? ", new String[]{String.valueOf(cursor.getLong(1))}, null, null, null, null));
        sigRelAut.setAdress(adress);
        sigRelAut.setName(cursor.getString(2));
        return sigRelAut;
    }

    private Adress cursorToAdress(Cursor cursor) {
        Adress adress = new Adress();
        adress.setId(cursor.getLong(0));
        adress.setStreet(cursor.getString(1));
        adress.setNumber(cursor.getString(2));
        adress.setParcels(cursor.getInt(3));
        return adress;
    }

    public List<SignatureReleaseAuthorisation> getAllSigRelAutOfAdress(Adress adress){
        List<SignatureReleaseAuthorisation> sigRelAutList = new ArrayList<>();
        Cursor cursor = db.query(
                PostmenDbHelper.TABLE_SIGRELAUT,
                allColumns,
                dbHelper.COLUMN_ADRESS_ID + " = ? ",
                new String[]{String.valueOf(adress.getId())},
                null, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) return sigRelAutList;

        while(!cursor.isAfterLast())
        {
            sigRelAutList.add(cursorToSigRelAut(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return null;
    }
}
