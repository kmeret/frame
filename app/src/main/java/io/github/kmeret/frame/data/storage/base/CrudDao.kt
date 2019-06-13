package io.github.kmeret.frame.data.storage.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class CrudDao<Item : Identifiable> {

    abstract fun getById(id: Long): Item?

    fun save(item: Item) {
        if (getById(item.id) == null)
            insert(item)
        else
            update(item)
    }

    fun saveAll(list: List<Item>) = list.forEach(::save)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(item: Item)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract fun insertAll(itemList: List<Item>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(item: Item)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun updateAll(itemList: List<Item>)

    @Delete
    abstract fun delete(item: Item)

    @Delete
    @JvmSuppressWildcards
    abstract fun deleteAll(itemList: List<Item>)

}
