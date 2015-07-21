package at.post.postmen.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

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

        return  null;
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
}
