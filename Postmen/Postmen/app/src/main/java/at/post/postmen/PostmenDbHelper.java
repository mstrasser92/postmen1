package at.post.postmen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 03.07.2015.
 */
public class PostmenDbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "postmen.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_ADRESSES = "Adresses";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_PARCELS = "parcels";

    public static final String SQL_CREATE =
            "create table" + TABLE_ADRESSES + "(" +
                    COLUMN_ID + " integer primary key autoincrement," +
                    COLUMN_STREET + " text not null," +
                    COLUMN_NUMBER + " integer not null," +
                    COLUMN_PARCELS + " integer;";

    public PostmenDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
