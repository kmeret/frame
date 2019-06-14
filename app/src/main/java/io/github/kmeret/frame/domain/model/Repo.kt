package io.github.kmeret.frame.domain.model

import io.github.kmeret.frame.infrastructure.presentation.list.ListItem

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
) : ListItem