package info.upump.russianapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import info.upump.russianapp.model.Author;
import info.upump.russianapp.model.Cover;

/**
 * Created by explo on 30.01.2018.
 */

public class CoverDao extends DataBaseDao implements IData<Cover> {
    public CoverDao(Context context) {
        super(context);
    }

    @Override
    public Cover save(Cover cover){
        ContentValues cv =  new ContentValues();
        cv.put(DBHelper.TABLE_KEY_TITLE, cover.getTitle());
        cv.put(DBHelper.TABLE_KEY_RATE, cover.getRate());
        cv.put(DBHelper.TABLE_KEY_FAVORITE, cover.isFavorite());
        cv.put(DBHelper.TABLE_KEY_READ, cover.isRead());
        cv.put(DBHelper.TABLE_KEY_ID_AUTHOR, cover.getAuthor().getId());
        cv.put(DBHelper.TABLE_KEY_IMG_COVER, cover.getImg());
        long insert = sqLiteDatabase.insert(DBHelper.TABLE_COVER, null, cv );
        if(insert<0){
          return null;
        }
        cover.setId(insert);
        return cover;
    }

    @Override
    public List<Cover> getAllFavorite() {
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_COVER,
                new String[]{
                        DBHelper.TABLE_KEY_ID,
                        DBHelper.TABLE_KEY_TITLE,
                        DBHelper.TABLE_KEY_RATE,
                        DBHelper.TABLE_KEY_FAVORITE,
                        DBHelper.TABLE_KEY_READ,
                        DBHelper.TABLE_KEY_ID_AUTHOR,
                        DBHelper.TABLE_KEY_IMG_COVER},
                DBHelper.TABLE_KEY_FAVORITE + "=? ", new String[]{"1"}, null, null, null, null
        );
        List<Cover> coverList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Cover cover = new Cover();
                cover.setId(cursor.getLong(0));
                cover.setTitle(cursor.getString(1));
                cover.setRate(cursor.getInt(2));
                cover.setFavorite(cursor.getInt(3) == 1);
                cover.setRead(cursor.getInt(4) == 1);
                cover.setAuthor(new Author(cursor.getLong(5)));
                cover.setImg(cursor.getString(6));
                coverList.add(cover);
            } while (cursor.moveToNext());
        }
        return coverList;
    }

    @Override
    public List<Cover> getAll() {
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_COVER,
                new String[]{
                        DBHelper.TABLE_KEY_ID,
                        DBHelper.TABLE_KEY_TITLE,
                        DBHelper.TABLE_KEY_RATE,
                        DBHelper.TABLE_KEY_FAVORITE,
                        DBHelper.TABLE_KEY_READ,
                        DBHelper.TABLE_KEY_ID_AUTHOR,
                        DBHelper.TABLE_KEY_IMG_COVER},
                null, null, null, null, null, null
        );
        List<Cover> coverList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Cover cover = new Cover();
                cover.setId(cursor.getLong(0));
                cover.setTitle(cursor.getString(1));
                cover.setRate(cursor.getInt(2));
                cover.setFavorite(cursor.getInt(3) == 1);
                cover.setRead(cursor.getInt(4) == 1);
                cover.setAuthor(new Author(cursor.getLong(5)));
                cover.setImg(cursor.getString(6));
                coverList.add(cover);
            } while (cursor.moveToNext());
        }

        return coverList;
    }

    @Override
    public List<Cover> getByParent(Cover cover) {
        return null;
    }

    public boolean update(Cover cover){
        ContentValues cv =  new ContentValues();
        cv.put(DBHelper.TABLE_KEY_TITLE, cover.getTitle());
        cv.put(DBHelper.TABLE_KEY_RATE, cover.getRate());
        cv.put(DBHelper.TABLE_KEY_FAVORITE, cover.isFavorite());
        cv.put(DBHelper.TABLE_KEY_READ, cover.isRead());
        cv.put(DBHelper.TABLE_KEY_ID_AUTHOR, cover.getAuthor().getId());
        cv.put(DBHelper.TABLE_KEY_IMG_COVER, cover.getImg());
        long update = sqLiteDatabase.update(DBHelper.TABLE_COVER, cv, DBHelper.TABLE_KEY_ID + " = " + cover.getId(), null);
        return update>0;
    }
}
