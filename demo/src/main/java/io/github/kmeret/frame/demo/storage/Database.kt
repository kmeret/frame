package io.github.kmeret.frame.demo.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kmeret.frame.demo.storage.dao.RepoDao
import io.github.kmeret.frame.demo.storage.dao.TopicDao
import io.github.kmeret.frame.demo.storage.dao.ProfileDao
import io.github.kmeret.frame.demo.storage.entity.RoomRepo
import io.github.kmeret.frame.demo.storage.entity.RoomTopic
import io.github.kmeret.frame.demo.storage.entity.RoomProfile

@Database(
        entities = [
            RoomProfile::class,
            RoomRepo::class,
            RoomTopic::class
        ],
        version = 1,
        exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
    abstract fun repoDao(): RepoDao
    abstract fun topicDao(): TopicDao

}