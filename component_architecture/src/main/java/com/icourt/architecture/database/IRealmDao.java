package com.icourt.architecture.database;

import java.util.List;

import io.realm.Case;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Description
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/4/6
 * version 1.0.0
 */
public interface IRealmDao<T extends RealmModel> {

    boolean isRealmAvailable();

    void releaseRealm();

    T insertFromJson(Class<T> c, String json);

    T insert(T t);

    T insertOrUpdate(T t);

    List<T> insert(Iterable<T> objects);

    void insertAsyn(Iterable<T> objects);

    List<T> insertOrUpdate(Iterable<T> objects);

    void insertOrUpdateAsyn(Iterable<T> objects);

    void delete(Class<T> c, String fieldName, String value);

    void delete(Class<T> c);

    RealmResults<T> queryAll(Class<T> c);

    RealmResults<T> queryAllAsync(Class<T> c);

    RealmResults<T> query(Class<T> c, String fieldName, String value);

    RealmResults<T> contains(Class<T> c, String fieldName, String value);

    RealmResults<T> contains(Class<T> c, String fieldName, String value, Case casing);

    T queryFirst(Class<T> c, String fieldName, String value);

    RealmResults<T> queryAll(RealmQuery<T> query);

    T queryFirst(RealmQuery<T> query);
}
