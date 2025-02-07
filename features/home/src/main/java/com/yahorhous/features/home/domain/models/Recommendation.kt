package com.yahorhous.features.home.domain.models

data class Recommendation(
    val id: String,
    val title: String,
    val link: String,
    val button:ButtonData
)

data class ButtonData(
    val text: String,
    val action: String? = null
)