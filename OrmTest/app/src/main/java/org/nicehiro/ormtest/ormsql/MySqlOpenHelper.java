package org.nicehiro.ormtest.ormsql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.nicehiro.ormtest.orm.DaoMaster;

/**
 * Created by hiro on 16-11-15.
 */

public class MySqlOpenHelper extends DaoMaster.OpenHelper {
    public MySqlOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
