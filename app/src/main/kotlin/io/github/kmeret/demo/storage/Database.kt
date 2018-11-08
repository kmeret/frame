package io.github.kmeret.demo.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kmeret.demo.storage.dao.RepoDao
import io.github.kmeret.demo.storage.dao.UserDao
import io.github.kmeret.demo.storage.entity.Repo
import io.github.kmeret.demo.storage.entity.User

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