package io.github.kmeret.frame.demo.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kmeret.frame.demo.storage.dao.RepoDao
import io.github.kmeret.frame.demo.storage.dao.UserDao
import io.github.kmeret.frame.demo.storage.entity.RoomRepo
import io.github.kmeret.frame.demo.storage.entity.RoomUser

@Database(
        entities = [
            RoomUser::class,
            RoomRepo::class
        ],
        version = 1,
        exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun repoDao(): RepoDao

}