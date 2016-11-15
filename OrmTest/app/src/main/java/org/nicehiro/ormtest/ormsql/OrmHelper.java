package org.nicehiro.ormtest.ormsql;

import android.content.Context;

import org.greenrobot.greendao.query.Query;
import org.nicehiro.ormtest.orm.ContactDao;
import org.nicehiro.ormtest.orm.DaoMaster;
import org.nicehiro.ormtest.orm.DaoSession;

import java.util.List;

/**
 * Created by hiro on 16-11-15.
 */

public class OrmHelper {

    private static OrmHelper instance;

    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public OrmHelper(Context context) {
        MySqlOpenHelper openHelper = new MySqlOpenHelper(context, "ORMDB");
        daoMaster = new DaoMaster(openHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static OrmHelper getInstance(Context context) {
        if (instance == null) {
            instance = new OrmHelper(context);
        }

        return instance;
    }

    public void insertORMContacts(List<Contact> contactList) {
        ContactDao contactDao = daoSession.getContactDao();
        contactDao.insertInTx(contactList);
    }

    public List<Contact> queryORMContact(int offset, int limit) {
        ContactDao contactDao = daoSession.getContactDao();
        Query<Contact> query = contactDao.queryBuilder()
                .orderDesc(ContactDao.Properties.Id).offset(offset).limit(limit)
                .build();

        return query.list();
    }
}
