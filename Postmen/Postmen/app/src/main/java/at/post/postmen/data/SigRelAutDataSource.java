package at.post.postmen.data;

import android.content.Context;
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

}
