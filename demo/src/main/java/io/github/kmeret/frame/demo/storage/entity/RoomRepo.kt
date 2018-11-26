package io.github.kmeret.frame.demo.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import io.github.kmeret.frame.demo.storage.base.Identifiable

@Entity(foreignKeys = [
    ForeignKey(
            entity = RoomUser::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
    )
], inheritSuperIndices = true)
data class RoomRepo(
        val name: String,
        val description: String,
        val starsCount: Int,
        @ColumnInfo(index = true)
        val userId: Long
) : Identifiable()