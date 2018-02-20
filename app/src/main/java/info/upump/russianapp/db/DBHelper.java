package info.upump.russianapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by explo on 29.01.2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public final static String DATABASE_NAME = "creepy.db";
    public final static String TABLE_COVER = "cover";
    public final static String TABLE_TALE = "tale";
    public final static String TABLE_AUTHOR = "author";
    public final static int DATA_BASE_VERSION = 2;

    public static final String TABLE_KEY_ID = "_id";
    public static final String TABLE_KEY_TITLE = "title";
    public static final String TABLE_KEY_RATE = "rate";
    public static final String TABLE_KEY_FAVORITE = "favorite";
    public static final String TABLE_KEY_READ = "read";
    public static final String TABLE_KEY_IMG_COVER = "img_cover";
    public static final String TABLE_KEY_ID_AUTHOR = "id_author";
    public static final String TABLE_KEY_IMG_CHAR = "img_char";

    public static final String TABLE_KEY_ID_COVER = "cover_id";
    public static final String TABLE_KEY_TEXT = "text";

    public static final String TABLE_KEY_NAME = "name";
    public static final String TABLE_KEY_EMAIL = "email";

    private static DBHelper instance;

    private static final String DB_PATH = "data/data/info.upump.russianapp/databases/" + DATABASE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `cover` ( `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `title` TEXT NOT NULL, `rate` INTEGER, `favorite` INTEGER NOT NULL, `read` INTEGER NOT NULL, `id_author` INTEGER, `img_cover` TEXT )");
        db.execSQL("CREATE TABLE `tale` ( `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `text` BLOB,"+TABLE_KEY_IMG_CHAR+" TEXT, "+"`cover_id` INTEGER NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }


    public void create_db() {
        InputStream myInput = null;
        OutputStream myOutput = null;

        if (checkBD()) {
            return;
        }

        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                //получаем локальную бд как поток в папке assets
                this.getReadableDatabase();
                myInput = context.getAssets().open(DATABASE_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;
                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);
                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
                close();

            }
            seVersionDB();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteBD() {
        File file = new File(DB_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    private void seVersionDB() {
        SQLiteDatabase sqLiteDatabase;
        try {
            sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.setVersion(DATA_BASE_VERSION);
            sqLiteDatabase.close();
        } catch (SQLiteException e) {

        }
    }

    private boolean checkBD() {
        SQLiteDatabase sqLiteDatabase;
        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
            int version = sqLiteDatabase.getVersion();
            sqLiteDatabase.close();
            if (version < DATA_BASE_VERSION) {
                deleteBD();
                return false;
            } else return true;
        } catch (SQLiteException e) {
            return false;
        }
    }
}
