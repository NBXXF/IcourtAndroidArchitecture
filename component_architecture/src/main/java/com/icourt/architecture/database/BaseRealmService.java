package com.icourt.architecture.database;

import io.realm.RealmObject;

/**
 * Description
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/4/6
 * version 1.0.0
 */
public abstract class BaseRealmService<D extends BaseDao<? extends RealmObject>> {
    protected D dao;

    public BaseRealmService(D d) {
        dao = d;
    }

    public boolean isServiceAvailable() {
        return dao.isRealmAvailable();
    }


    public void releaseService() {
        dao.releaseRealm();
    }
}
