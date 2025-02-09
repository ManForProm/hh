package com.yahorhous.features.home.domain.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.yahorhous.core.design.design.icons.AppIcons

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

data class RecomendationIconModel(
    val icon: Painter,
    val colorRoundIcon: Color
)
