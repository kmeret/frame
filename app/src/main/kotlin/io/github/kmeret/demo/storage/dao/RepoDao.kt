package io.github.kmeret.demo.storage.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.kmeret.demo.storage.base.CrudDao
import io.github.kmeret.demo.storage.entity.Repo

@Dao
abstract class RepoDao : CrudDao<Repo>() {

    @Query("select * from repo where id = :id")
    abstract override fun getById(id: Long): Repo?

}