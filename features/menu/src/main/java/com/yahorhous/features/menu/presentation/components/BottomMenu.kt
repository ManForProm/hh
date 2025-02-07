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
            containerColor = MaterialTheme.colorScheme.background, // Фон через NavigationBar
            modifier = Modifier.height(85.dp)
        ) {
            // Поиск
            NavigationBarItem(
                selected = selectedTab == MenuTab.Search,
                onClick = { onTabSelected(MenuTab.Search) },
                icon = {
                    Icon(
                        painter = AppIcons.Common.SearchActive.painterResource(),
                        contentDescription = "Поиск"
                    )
                },
                label = { Text(text = "Поиск",
                        fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 11.sp ) },
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
                            painter = AppIcons.Common.SearchDefault.painterResource(),
                            contentDescription = "Избранное"
                        )
                    }
                },
                label = { Text("Избранное" ,
                    fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 11.sp ) },
                alwaysShowLabel = true
            )

            NavigationBarItem(
                selected = selectedTab == MenuTab.Responses,
                onClick = { onTabSelected(MenuTab.Responses) },
                icon = {
                    Icon(
                        AppIcons.Common.ResponsesDefault.painterResource(),
                        contentDescription = "Отклики"
                    )
                },
                label = { Text("Отклики", fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 11.sp ) },
                alwaysShowLabel = true
            )

            NavigationBarItem(
                selected = selectedTab == MenuTab.Messages,
                onClick = { onTabSelected(MenuTab.Messages) },
                icon = {
                    Icon(
                        AppIcons.Common.MessagesDefault.painterResource(),
                        contentDescription = "Сообщения"
                    )
                },
                label = { Text("Сообщения", fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 11.sp ) },
                alwaysShowLabel = true
            )

            NavigationBarItem(
                selected = selectedTab == MenuTab.Profile,
                onClick = { onTabSelected(MenuTab.Profile) },
                icon = {
                    Icon(
                        AppIcons.Common.ProfileDefault.painterResource(),
                        contentDescription = "Профиль"
                    )
                },
                label = { Text("Профиль", fontSize = 11.sp,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 11.sp ) },
                alwaysShowLabel = true
            )

        }
}