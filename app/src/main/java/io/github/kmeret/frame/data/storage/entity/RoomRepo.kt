package io.github.kmeret.frame.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.kmeret.frame.data.storage.base.Identifiable

@Entity
data class RoomRepo(
        @PrimaryKey
        @ColumnInfo(index = true)
        override val id: Long,
        val name: String,
        val fullName: String,
        val description: String,
        val language: String,
        val starsCount: Int,
        val forksCount: Int,
        val updatedAt: String
) : Identifiable