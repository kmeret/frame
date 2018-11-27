package io.github.kmeret.frame.demo.storage.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.kmeret.frame.demo.storage.base.CrudDao
import io.github.kmeret.frame.demo.storage.entity.RoomUser

@Dao
abstract class UserDao : CrudDao<RoomUser>() {

    @Query("select * from RoomUser where id = :id")
    abstract override fun getById(id: Long): RoomUser?

}