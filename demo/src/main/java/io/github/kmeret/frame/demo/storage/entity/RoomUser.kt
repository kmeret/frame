package io.github.kmeret.frame.demo.storage.entity

import androidx.room.Entity
import io.github.kmeret.frame.demo.storage.base.Identifiable

@Entity(inheritSuperIndices = true)
data class RoomUser(
        var login: String,
        var avatarUrl: String
) : Identifiable()