package org.nicehiro.ormtest.ormsql;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.nicehiro.ormtest.orm.DaoMaster;

/**
 * Created by hiro on 16-11-15.
 */

public class MySqlOpenHelper extends DaoMaster.OpenHelper {
    public MySqlOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

    }
}
