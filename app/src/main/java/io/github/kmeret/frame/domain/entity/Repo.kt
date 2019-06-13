package io.github.kmeret.frame.domain.entity

import io.github.kmeret.frame.android.ui.RecyclerAdapter

data class Repo(
        override val id: Long,
        val name: String,
        val fullName: String,
        val description: String,
        val language: String,
        val starsCount: Int,
        val forksCount: Int,
        val topics: List<String>,
        val updatedAt: String
) : RecyclerAdapter.Identifiable