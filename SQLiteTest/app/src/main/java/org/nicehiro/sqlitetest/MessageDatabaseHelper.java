package org.nicehiro.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-11.
 */

public class MessageDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "message.db";
    private static final int DB_VERSION = 1;

    private static class MessageTable {
        private static final String TABLE_NAME = "message";

        private static final String COLUMN_NAME_ID = "_id";
        private static final String COLUMN_NAME_FROM_NAME = "from_name";
        private static final String COLUMN_NAME_TO_NAME = "to_name";
        private static final String COLUMN_NAME_TIME = "time";
        private static final String COLUMN_NAME_CONTENT = "content";
        private static final String COLUMN_NAME_STATE = "state";

        private static final String COLUMN_NAME_TYPE = "type";
    }

    public MessageDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createVersion1(sqLiteDatabase);

//        update database
//        createVersion2(sqLiteDatabase);
    }

    private void createVersion1 (SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MessageTable.TABLE_NAME + " ("
                + MessageTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
                + MessageTable.COLUMN_NAME_FROM_NAME + " TEXT,"
                + MessageTable.COLUMN_NAME_TO_NAME + " TEXT,"
                + MessageTable.COLUMN_NAME_TIME + " INTEGER,"
                + MessageTable.COLUMN_NAME_CONTENT + " TEXT,"
                + MessageTable.COLUMN_NAME_STATE + " INTEGER"
                + ");");
    }

    private void createVersion2 (SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MessageTable.TABLE_NAME + " ("
                + MessageTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
                + MessageTable.COLUMN_NAME_FROM_NAME + " TEXT,"
                + MessageTable.COLUMN_NAME_TO_NAME + " TEXT,"
                + MessageTable.COLUMN_NAME_TIME + " INTEGER,"
                + MessageTable.COLUMN_NAME_CONTENT + " TEXT,"
                + MessageTable.COLUMN_NAME_STATE + " INTEGER"
                + MessageTable.COLUMN_NAME_TYPE + " INTEGER DEFAULT 1"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            sqLiteDatabase.execSQL("ALTER TABLE " + MessageTable.TABLE_NAME + " ADD "
                    + MessageTable.COLUMN_NAME_TYPE + " INTEGER DEFAULT 1");
        }
    }

    public void insertMessage (Message message) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageTable.COLUMN_NAME_ID, message.getId());
        contentValues.put(MessageTable.COLUMN_NAME_FROM_NAME, message.getFromName());
        contentValues.put(MessageTable.COLUMN_NAME_TO_NAME, message.getToName());
        contentValues.put(MessageTable.COLUMN_NAME_TIME, message.getTime());
        contentValues.put(MessageTable.COLUMN_NAME_CONTENT, message.getContent());
        contentValues.put(MessageTable.COLUMN_NAME_STATE, message.getState());

        db.insertWithOnConflict(MessageTable.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void removeMessage (long id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(MessageTable.TABLE_NAME, MessageTable.COLUMN_NAME_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public void updateMessage (long id, int state) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageTable.COLUMN_NAME_STATE, state);

        db.update(MessageTable.TABLE_NAME, contentValues, MessageTable.COLUMN_NAME_ID + " = ?", new String[] {String.valueOf(id)});
    }

    private String[] projection = {
            MessageTable.COLUMN_NAME_ID,
            MessageTable.COLUMN_NAME_FROM_NAME,
            MessageTable.COLUMN_NAME_TO_NAME,
            MessageTable.COLUMN_NAME_TIME,
            MessageTable.COLUMN_NAME_CONTENT,
            MessageTable.COLUMN_NAME_STATE,
    };

    public List<Message> searchAllMessage () {
        SQLiteDatabase db = getWritableDatabase();

        List<Message> messageList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(MessageTable.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    MessageTable.COLUMN_NAME_TIME + " ASC");

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(cursor.getColumnIndex(MessageTable.COLUMN_NAME_ID));
                    String fromName = cursor.getString(cursor.getColumnIndex(MessageTable.COLUMN_NAME_FROM_NAME));
                    String toName = cursor.getString(cursor.getColumnIndex(MessageTable.COLUMN_NAME_TO_NAME));
                    long time = cursor.getLong(cursor.getColumnIndex(MessageTable.COLUMN_NAME_TIME));
                    String content = cursor.getString(cursor.getColumnIndex(MessageTable.COLUMN_NAME_CONTENT));
                    int state = cursor.getInt(cursor.getColumnIndex(MessageTable.COLUMN_NAME_STATE));

                    Message message = new Message(id, fromName, toName, time, content, state);
                    messageList.add(message);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return messageList;
    }
}
