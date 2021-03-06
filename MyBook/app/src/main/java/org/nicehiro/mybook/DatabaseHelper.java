package org.nicehiro.mybook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hiro on 17-3-19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Book(" +
                // "id integer primary key autoincrement," +
                "isbn13 text primary key not null," +
                "title text not null," +
                "author text not null," +
                "image text not null," +
                "tags text not null," +
                "publisher text not null," +
                "price float not null," +
                "bookmark integer default 0)");

        db.execSQL("create table if not exists Comments(" +
                "isbn13 text primary key not null," +
                "title text not null," +
                "comment text not null)");

        db.execSQL("create table if not exists MarkedBook(" +
                "isbn13 text primary key not null," +
                "title text not null," +
                "author text not null," +
                "time text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            case 2:
        }
    }
}
