package org.nicehiro.myshopcar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiro on 16-11-17.
 */

public class MyHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "message.db";
    private static final int DB_VERSION = 1;

    private static class GoodsTable {
        private static final String NAME = "goods";
        private static final String ID = "_id";
        private static final String DESC = "desc";
        private static final String PRICE = "price";
        private static final String HEAD_LINK = "head_link";
        private static final String REMAIN = "remain";
        private static final String TIME = "time";
    }

    public MyHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void insert(Goods goods) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(GoodsTable.ID, goods.getId());
        contentValues.put(GoodsTable.DESC, goods.getDesc());
        contentValues.put(GoodsTable.PRICE, goods.getPrice());
        contentValues.put(GoodsTable.HEAD_LINK, goods.getHead_link());
        contentValues.put(GoodsTable.REMAIN, goods.getRemain());
        contentValues.put(GoodsTable.TIME, goods.getTime());

        db.insertWithOnConflict(GoodsTable.NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    private String[] projection = {
            GoodsTable.ID,
            GoodsTable.DESC,
            GoodsTable.PRICE,
            GoodsTable.HEAD_LINK,
            GoodsTable.REMAIN,
            GoodsTable.TIME
    };

    public List<Goods> query() {
        SQLiteDatabase db = getWritableDatabase();

        List<Goods> goodsList = new ArrayList<>();
        Cursor cursor = null;

        cursor = db.query(GoodsTable.NAME, projection, null, null, null, null,
                GoodsTable.TIME + " DESC");

        if (cursor != null && cursor.moveToNext()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(GoodsTable.ID));
                String desc = cursor.getString(cursor.getColumnIndex(GoodsTable.DESC));
                float price = cursor.getFloat(cursor.getColumnIndex(GoodsTable.PRICE));
                String headLink = cursor.getString(cursor.getColumnIndex(GoodsTable.HEAD_LINK));
                int remain = cursor.getInt(cursor.getColumnIndex(GoodsTable.REMAIN));
                String time = cursor.getString(cursor.getColumnIndex(GoodsTable.TIME));

                Goods goods = new Goods(id, desc, price, headLink, remain, time);
                goodsList.add(goods);
            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return goodsList;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createSQL = "create table " + GoodsTable.NAME + " ("
                + GoodsTable.ID + " varchar(16) not null,"
                + GoodsTable.DESC + " text,"
                + GoodsTable.PRICE + " float,"
                + GoodsTable.HEAD_LINK + " text,"
                + GoodsTable.REMAIN + " integer,"
                + GoodsTable.TIME + " text"
                + ");";

        sqLiteDatabase.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
