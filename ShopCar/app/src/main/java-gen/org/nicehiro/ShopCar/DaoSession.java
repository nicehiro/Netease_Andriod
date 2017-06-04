package org.nicehiro.ShopCar;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import org.nicehiro.ShopCar.shopcar;

import org.nicehiro.ShopCar.shopcarDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shopcarDaoConfig;

    private final shopcarDao shopcarDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shopcarDaoConfig = daoConfigMap.get(shopcarDao.class).clone();
        shopcarDaoConfig.initIdentityScope(type);

        shopcarDao = new shopcarDao(shopcarDaoConfig, this);

        registerDao(shopcar.class, shopcarDao);
    }
    
    public void clear() {
        shopcarDaoConfig.getIdentityScope().clear();
    }

    public shopcarDao getShopcarDao() {
        return shopcarDao;
    }

}
