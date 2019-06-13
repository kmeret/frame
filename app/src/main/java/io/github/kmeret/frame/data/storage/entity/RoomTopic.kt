package io.github.kmeret.frame.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.github.kmeret.frame.data.storage.base.Identifiable

@Entity(foreignKeys = [
    ForeignKey(
            entity = RoomRepo::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("repoId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
    )
])
data class RoomTopic(
        @PrimaryKey
        @ColumnInfo(index = true)
        override val id: Long,
        val name: String,
        @ColumnInfo(index = true)
        val repoId: Long
) : Identifiable