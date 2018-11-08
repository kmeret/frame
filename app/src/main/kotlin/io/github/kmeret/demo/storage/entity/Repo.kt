package io.github.kmeret.demo.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import io.github.kmeret.demo.storage.base.Identifiable

@Entity(foreignKeys = [
    ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
    )
], inheritSuperIndices = true)
data class Repo(
        val name: String,
        val description: String,
        val starsCount: Int,
        @ColumnInfo(index = true)
        val userId: Long
) : Identifiable()