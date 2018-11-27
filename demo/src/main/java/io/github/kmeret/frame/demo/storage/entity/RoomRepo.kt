package io.github.kmeret.frame.demo.storage.entity

import androidx.room.Entity
import io.github.kmeret.frame.demo.storage.base.Identifiable

@Entity(inheritSuperIndices = true)
data class RoomRepo(
        val name: String,
        val fullName: String,
        val description: String,
        val language: String,
        val starsCount: Int,
        val forksCount: Int,
        val updatedAt: String
) : Identifiable()