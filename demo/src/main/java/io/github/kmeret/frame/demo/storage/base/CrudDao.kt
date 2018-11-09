package io.github.kmeret.frame.demo.storage.base

import androidx.room.*

abstract class CrudDao<T : Identifiable> {

    abstract fun getById(id: Long): T?

    fun save(item: T) {
        if (getById(item.id) == null)
            insert(item)
        else
            update(item)
    }

    fun saveAll(list: List<T>) = list.forEach(::save)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract fun insertAll(itemList: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(item: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun updateAll(itemList: List<T>)

    @Delete
    abstract fun delete(item: T)

    @Delete
    @JvmSuppressWildcards
    abstract fun deleteAll(itemList: List<T>)

}
