package io.github.kmeret.frame.infrastructure.data.storage

import io.realm.Realm
import io.realm.RealmModel
import io.realm.kotlin.where

class RealmDao {

    fun save(data: RealmModel) {
        safeTransaction {
            it.insert(data)
        }
    }

    inline fun <reified T : RealmModel> findFirst(): T? {
        return Realm.getDefaultInstance().use {
            it.where<T>().findFirstAsync()
        }
    }

    fun deleteAll() = safeTransaction { it.deleteAll() }

    private fun safeTransaction(realm: (Realm) -> Unit) {
        safeRealm { instance ->
            instance.executeTransactionAsync(realm)
        }
    }

    private fun safeRealm(realm: (Realm) -> Unit) {
        return Realm.getDefaultInstance().use(realm)
    }

}