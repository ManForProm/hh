package com.yahorhous.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yahorhous.favorites.presentation.screen.FavoritesScreen
import com.yahorhous.features.home.presentation.screen.HomeScreen
import com.yahorhous.features.menu.presentation.model.MenuTab

@Composable
fun NavGraph(modifier: Modifier,
             navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        // Главный экран
        composable(MenuTab.Search.route) {
            HomeScreen(
//                onVacancyClick = { /*...*/ }
            )
        }

        // Избранное
        composable(MenuTab.Favorites.route) {
            FavoritesScreen()
        }
        composable(MenuTab.Messages.route) {
        }
        composable(MenuTab.Responses.route) {
        }
        composable(MenuTab.Profile.route) {
        }
    }
}