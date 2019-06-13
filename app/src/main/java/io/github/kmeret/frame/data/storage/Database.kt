package io.github.kmeret.frame.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kmeret.frame.data.storage.dao.ProfileDao
import io.github.kmeret.frame.data.storage.dao.RepoDao
import io.github.kmeret.frame.data.storage.dao.TopicDao
import io.github.kmeret.frame.data.storage.entity.RoomProfile
import io.github.kmeret.frame.data.storage.entity.RoomRepo
import io.github.kmeret.frame.data.storage.entity.RoomTopic

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