package at.post.postmen.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 03.07.2015.
 */
public class PostmenDbHelper extends SQLiteOpenHelper{

    // DATABASE NAME
    public static final String DB_NAME = "postmen.db";
    public static final int DB_VERSION = 1;

    // COMMON COLUMNS
    public static final String COLUMN_ID = "id";

    // TABLE ADRESSES
    public static final String TABLE_ADRESSES = "Adresses";

    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_PARCELS = "parcels";

    public static final String SQL_CREATE_TABLE_ADRESSES =
            "create table " + TABLE_ADRESSES + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_STREET + " text not null, " +
                    COLUMN_NUMBER + " text not null, " +
                    COLUMN_PARCELS + " integer, " +
                    "UNIQUE(" + COLUMN_STREET + ", " + COLUMN_NUMBER + ") ON CONFLICT REPLACE);";


    // TABLE SIGNATURE RELEASE AUTHORISATIONS
    public static final String TABLE_SIGRELAUT = "SignatureReleaseAuthorisations";
    public static final String COLUMN_ADRESS_ID = "adressId";
    public static final String COLUMN_NAME = "name";

    public static final String SQL_CREATE_TABLE_SIGRELAUT =
            "create table " + TABLE_SIGRELAUT + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_ADRESS_ID + " integer not null, " +
                    COLUMN_NAME + " text not null, " +
                    "UNIQUE(" + COLUMN_ADRESS_ID + ", " + COLUMN_NAME + ") ON CONFLICT REPLACE);";

    public PostmenDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ADRESSES);
        db.execSQL(SQL_CREATE_TABLE_SIGRELAUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
