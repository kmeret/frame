package io.github.kmeret.frame.domain.entity

import io.github.kmeret.frame.android.ui.RecyclerAdapter

data class User(
        override val id: Long,
        val login: String,
        val avatarUrl: String
) : RecyclerAdapter.Identifiable