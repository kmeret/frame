package io.github.kmeret.frame.data.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.kmeret.frame.data.storage.base.CrudDao
import io.github.kmeret.frame.data.storage.entity.RoomProfile

@Dao
abstract class ProfileDao : CrudDao<RoomProfile>() {

    @Query("select * from RoomProfile where id = :id")
    abstract override fun getById(id: Long): RoomProfile?

    @Query("select * from RoomProfile where id = :id")
    abstract fun getLiveById(id: Long): LiveData<RoomProfile?>

}