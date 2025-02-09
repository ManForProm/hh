package com.yahorhous.features.menu.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yahorhous.core.design.design.icons.AppIcons
import com.yahorhous.core.design.design.icons.painterResource
import com.yahorhous.features.menu.presentation.model.MenuTab

@Composable
fun BottomMenu(
    selectedTab: MenuTab,
    favoritesCount: Int,
    onTabSelected: (MenuTab) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.height(85.dp)
    ) {
        // Поиск
        NavigationBarItem(
            selected = selectedTab == MenuTab.Search,
            onClick = { onTabSelected(MenuTab.Search) },
            icon = {
                Icon(
                    painter = if (selectedTab == MenuTab.Search) {
                        AppIcons.Common.SearchActive.painterResource()
                    } else {
                        AppIcons.Common.SearchDefault.painterResource()
                    },
                    contentDescription = "Поиск"
                )
            },
            label = {
                Text(
                    text = "Поиск",
                    fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selectedTab == MenuTab.Search) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onPrimary
                    }
                )
            },
            alwaysShowLabel = true
        )

        // Избранное
        NavigationBarItem(
            selected = selectedTab == MenuTab.Favorites,
            onClick = { onTabSelected(MenuTab.Favorites) },
            icon = {
                BadgedBox(badge = {
                    if (favoritesCount > 0) {
                        Badge { Text(favoritesCount.toString()) }
                    }
                }) {
                    Icon(
                        painter = if (selectedTab == MenuTab.Favorites) {
                            AppIcons.Common.FavoriteDefault.painterResource()
                        } else {
                            AppIcons.Common.FavoriteDefault.painterResource()
                        },
                        contentDescription = "Избранное"
                    )
                }
            },
            label = {
                Text(
                    text = "Избранное",
                    fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selectedTab == MenuTab.Favorites) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onPrimary
                    }
                )
            },
            alwaysShowLabel = true
        )

        // Отклики
        NavigationBarItem(
            selected = selectedTab == MenuTab.Responses,
            onClick = { onTabSelected(MenuTab.Responses) },
            icon = {
                Icon(
                    painter = if (selectedTab == MenuTab.Responses) {
                        AppIcons.Common.ResponsesActive.painterResource()
                    } else {
                        AppIcons.Common.ResponsesDefault.painterResource()
                    },
                    contentDescription = "Отклики"
                )
            },
            label = {
                Text(
                    text = "Отклики",
                    fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selectedTab == MenuTab.Responses) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onPrimary
                    }
                )
            },
            alwaysShowLabel = true
        )

        // Сообщения
        NavigationBarItem(
            selected = selectedTab == MenuTab.Messages,
            onClick = { onTabSelected(MenuTab.Messages) },
            icon = {
                Icon(
                    painter = if (selectedTab == MenuTab.Messages) {
                        AppIcons.Common.MessagesDefault.painterResource()
                    } else {
                        AppIcons.Common.MessagesDefault.painterResource()
                    },
                    contentDescription = "Сообщения"
                )
            },
            label = {
                Text(
                    text = "Сообщения",
                    fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selectedTab == MenuTab.Messages) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onPrimary
                    }
                )
            },
            alwaysShowLabel = true
        )

        // Профиль
        NavigationBarItem(
            selected = selectedTab == MenuTab.Profile,
            onClick = { onTabSelected(MenuTab.Profile) },
            icon = {
                Icon(
                    painter = if (selectedTab == MenuTab.Profile) {
                        AppIcons.Common.ProfileActive.painterResource()
                    } else {
                        AppIcons.Common.ProfileDefault.painterResource()
                    },
                    contentDescription = "Профиль"
                )
            },
            label = {
                Text(
                    text = "Профиль",
                    fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selectedTab == MenuTab.Profile) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onPrimary
                    }
                )
            },
            alwaysShowLabel = true
        )
    }
}