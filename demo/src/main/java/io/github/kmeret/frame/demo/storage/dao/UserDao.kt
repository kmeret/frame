package io.github.kmeret.frame.demo.storage.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.kmeret.frame.demo.storage.base.CrudDao
import io.github.kmeret.frame.demo.storage.entity.User

@Dao
abstract class UserDao : CrudDao<User>() {

    @Query("select * from user where id = :id")
    abstract override fun getById(id: Long): User?

}