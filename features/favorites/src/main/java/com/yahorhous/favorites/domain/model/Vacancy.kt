package com.yahorhous.favorites.domain.model


data class Vacancy(
    val id: Int,
    val viewers: Int,
    val title: String,
    val salary: String,
    val location: String,
    val category: String,
    val experience: String,
    val datePublished: String,
    val isFavorite: Boolean = true
)