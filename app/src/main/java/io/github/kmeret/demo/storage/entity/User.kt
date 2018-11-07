package io.github.kmeret.demo.storage.entity

import androidx.room.Entity
import io.github.kmeret.demo.storage.base.Identifiable

@Entity(inheritSuperIndices = true)
data class User(
        var login: String,
        var avatarUrl: String
) : Identifiable()