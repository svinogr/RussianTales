package info.upump.russianapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by explo on 30.01.2018.
 */

public class DataBaseDao {
    protected SQLiteDatabase sqLiteDatabase;
    protected DBHelper dbHelper;

    public DataBaseDao(Context context) {
        dbHelper = DBHelper.getHelper(context);
        open();
    }

    protected void open() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    protected void close(){
        if(sqLiteDatabase != null){
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
    }
}
