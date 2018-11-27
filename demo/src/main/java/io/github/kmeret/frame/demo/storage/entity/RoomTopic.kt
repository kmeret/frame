package io.github.kmeret.frame.demo.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import io.github.kmeret.frame.demo.storage.base.Identifiable

@Entity(foreignKeys = [
    ForeignKey(
            entity = RoomRepo::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("repoId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
    )
], inheritSuperIndices = true)
data class RoomTopic(
        val name: String,
        @ColumnInfo(index = true)
        val repoId: Long
) : Identifiable()