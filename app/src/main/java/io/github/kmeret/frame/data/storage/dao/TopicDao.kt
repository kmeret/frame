package io.github.kmeret.frame.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.kmeret.frame.data.storage.base.CrudDao
import io.github.kmeret.frame.data.storage.entity.RoomTopic

@Dao
abstract class TopicDao : CrudDao<RoomTopic>() {

    @Query("select * from RoomTopic where id = :id")
    abstract override fun getById(id: Long): RoomTopic?

}