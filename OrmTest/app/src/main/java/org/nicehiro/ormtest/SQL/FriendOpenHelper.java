package org.nicehiro.ormtest.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiro on 16-11-14.
 */

public class FriendOpenHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "RAWDBs";
    private static final String T_CONTACT = "contacts";

    public FriendOpenHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    private interface DB_FIELD_CONTACT {
        public static final String F_ID = "id";
        public static final String F_UID = "uid";
        public static final String F_YID = "yid";
        public static final String F_NICKNAME = "nickname";
        public static final String F_GENDER = "gender";
        public static final String F_MOBILE = "mobile";
        public static final String F_PHOTOURL = "photourl";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CONTACT_CREATE =
                "create table if not exists " + T_CONTACT +
                        " (" + DB_FIELD_CONTACT.F_ID + " integer auto_increment, " +
                        DB_FIELD_CONTACT.F_UID + " varchar(16) not null ," +
                        DB_FIELD_CONTACT.F_YID + " varchar(16), " +
                        DB_FIELD_CONTACT.F_NICKNAME + " varchar(16), " +
                        DB_FIELD_CONTACT.F_GENDER + " integer, " +
                        DB_FIELD_CONTACT.F_MOBILE + " varchar(16), " +
                        DB_FIELD_CONTACT.F_PHOTOURL + " varchar(32))";

        sqLiteDatabase.execSQL(CONTACT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Contact> queryTimeLine (int offset, int limit) {
        String selectSQL = String.format("select id, uid, yid, gender, " +
            "mobile, photourl from contacts " +
            "order by id DESC limit %d, %d", offset, limit);

        Cursor cursor = getReadableDatabase().rawQuery(selectSQL, null);

        if (cursor != null) {
            ArrayList<Contact> contactArrayList = new ArrayList<>();
            while (cursor.moveToNext()) {
                int column = 0;
                Contact contact = new Contact();
                contact.setId(cursor.getInt(column ++));
                contact.setuId(cursor.getString(column ++));
                contact.setyId(cursor.getString(column ++));
                contact.setGender(cursor.getInt(column ++));
                contact.setMobile(cursor.getString(column ++));
                contact.setPhotoUrl(cursor.getString(column));

                contactArrayList.add(contact);
            }

            cursor.close();
            return contactArrayList;
        } else {
            return new ArrayList<Contact>();
        }
    }

    public void insertOrUpdateContact (Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", contact.getuId());
        contentValues.put("yid", contact.getyId());
        contentValues.put("gender", contact.getGender());
        contentValues.put("mobile", contact.getMobile());
        contentValues.put("photourl", contact.getPhotoUrl());

        getWritableDatabase().insertWithOnConflict(T_CONTACT, null,
                contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }
}
