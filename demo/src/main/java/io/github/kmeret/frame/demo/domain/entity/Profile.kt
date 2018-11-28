package io.github.kmeret.frame.demo.domain.entity

data class Profile(
        val id: Long,
        val login: String,
        val avatarUrl: String,
        val name: String,
        val bio: String,
        val company: String,
        val location: String
)