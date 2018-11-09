package io.github.kmeret.frame.demo.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kmeret.frame.demo.storage.dao.RepoDao
import io.github.kmeret.frame.demo.storage.dao.UserDao
import io.github.kmeret.frame.demo.storage.entity.Repo
import io.github.kmeret.frame.demo.storage.entity.User

@Database(
        entities = [
            User::class,
            Repo::class
        ],
        version = 1,
        exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun repoDao(): RepoDao

}