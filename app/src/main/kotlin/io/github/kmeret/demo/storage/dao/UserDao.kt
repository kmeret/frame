package io.github.kmeret.demo.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.kmeret.demo.storage.base.CrudDao
import io.github.kmeret.demo.storage.entity.User

@Dao
abstract class UserDao : CrudDao<User>() {

    @Query("select * from user where id = :id")
    abstract override fun getById(id: Long): User?

}