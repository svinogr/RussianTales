package info.upump.russianapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import info.upump.russianapp.model.Cover;
import info.upump.russianapp.model.Tale;

/**
 * Created by explo on 03.02.2018.
 */

public class TaleDao extends DataBaseDao implements IData<Tale> {
    public TaleDao(Context context) {
        super(context);
    }

    @Override
    public List<Tale> getAll() {
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_TALE,
                new String[]{
                        DBHelper.TABLE_KEY_ID,
                        DBHelper.TABLE_KEY_TEXT,
                        DBHelper.TABLE_KEY_IMG_CHAR,
                        DBHelper.TABLE_KEY_ID_COVER,
                },
                null, null, null, null, null, null
        );
        List<Tale> taleList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Tale tale = new Tale();
                tale.setId(cursor.getLong(0));
                tale.setText(cursor.getString(1));
                tale.setImgChar(cursor.getString(2));
                tale.setCover(new Cover(cursor.getLong(3)));
                taleList.add(tale);
            } while (cursor.moveToNext());
        }
        return taleList;
    }

    @Override
    public List<Tale> getByParent(Cover cover) {
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_TALE,
                new String[]{
                        DBHelper.TABLE_KEY_ID,
                        DBHelper.TABLE_KEY_TEXT,
                        DBHelper.TABLE_KEY_IMG_CHAR,
                        DBHelper.TABLE_KEY_ID_COVER,
                },
                DBHelper.TABLE_KEY_ID_COVER + " =? ", new String[]{String.valueOf(cover.getId())}, null, null, null, null
        );
        List<Tale> taleList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Tale tale = new Tale();
                tale.setId(cursor.getLong(0));
                tale.setText(cursor.getString(1));
                tale.setImgChar(cursor.getString(2));
                tale.setCover(new Cover(cursor.getLong(3)));
                taleList.add(tale);
            } while (cursor.moveToNext());
        }
        return taleList;
    }

    @Override
    public Tale save(Tale tale) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.TABLE_KEY_TEXT, tale.getText());
        cv.put(DBHelper.TABLE_KEY_IMG_CHAR, tale.getImgChar());
        cv.put(DBHelper.TABLE_KEY_ID_COVER, tale.getCover().getId());
        long insert = sqLiteDatabase.insert(DBHelper.TABLE_TALE, null, cv);
        if(insert<0){return null;}
        tale.setId(insert);
        return tale;

    }

    @Override
    public List<Tale> getAllFavorite() {
        return null;
    }

}
