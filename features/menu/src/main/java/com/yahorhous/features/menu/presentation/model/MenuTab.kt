package com.yahorhous.features.menu.presentation.model

import androidx.compose.ui.graphics.painter.Painter
import com.yahorhous.core.design.design.icons.AppIcons
import com.yahorhous.features.menu.R

sealed class MenuTab(
    val route:String,
    val icon: Int,
    val label: String,
) {
    object Search : MenuTab(
        route = "home",
        icon = AppIcons.Common.SearchDefault,
        label = "Поиск"
    )

    object Favorites : MenuTab(
        route = "favorites",
        icon = AppIcons.Common.FavoriteDefault,
        label = "Избранное"
    )

    object Responses : MenuTab(
        route = "responses",
        icon = AppIcons.Common.ResponsesDefault,
        label = "Отклики"
    )

    object Messages : MenuTab(
        route = "messages",
        icon = AppIcons.Common.MessagesDefault,
        label = "Сообщения"
    )
    object Profile : MenuTab(
        route = "profile",
        icon = AppIcons.Common.ProfileDefault,
        label = "Профиль"
    )

    companion object {
        fun fromRoute(route: String?): MenuTab {
            return when (route?.substringBefore("/")) {
                Search.route -> Search
                Favorites.route -> Favorites
                Profile.route -> Profile
                Messages.route -> Messages
                Responses.route -> Responses
                else -> Search
            }
        }
    }
}