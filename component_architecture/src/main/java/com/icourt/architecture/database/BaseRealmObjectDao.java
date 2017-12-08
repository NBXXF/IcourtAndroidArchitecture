package com.icourt.architecture.database;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObject;

/**
 * Description
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/4/6
 * version 1.0.0
 */
public class BaseRealmObjectDao<T extends RealmObject> extends BaseDao<T> {
    public BaseRealmObjectDao(Realm realm) {
        super(realm);
    }

    public BaseRealmObjectDao(@NonNull String realmName, @NonNull byte[] encryptionKey, int schemaVersion, @NonNull RealmMigration migration) {
        super(Realm.getInstance(new RealmConfiguration.Builder()
                .name(realmName)
                .encryptionKey(encryptionKey)
                .schemaVersion(schemaVersion)
                .deleteRealmIfMigrationNeeded()
                .migration(migration)
                .build()));
    }

    public BaseRealmObjectDao(@NonNull String realmName, int schemaVersion, @NonNull RealmMigration migration) {
        super(Realm.getInstance(new RealmConfiguration.Builder()
                .name(realmName)
                .schemaVersion(schemaVersion)
                .deleteRealmIfMigrationNeeded()
                .migration(migration)
                .build()));
    }


    @Override
    public void delete(T t) {
        if (!isRealmAvailable()) return;
        try {
            realm.beginTransaction();
            t.deleteFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }
}
