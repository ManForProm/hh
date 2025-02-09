package com.yahorhous.favorites.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yahorhous.core.network.model.Vacancy
import com.yahorhous.favorites.presentation.viewmodel.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(){
    val favoritesViewModel: FavoritesViewModel = koinViewModel()
    val favorites by favoritesViewModel.favoritesDetails.collectAsState(initial = emptyList())
    FavoritesScreenInner(favorites = favorites, onFavoriteToggle = {vacancy -> favoritesViewModel.toggleFavorite(vacancy)})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreenInner(
    favorites: List<Vacancy> = emptyList(),
    onRespond: (Vacancy) -> Unit = { },
    onFavoriteToggle: (Vacancy) -> Unit = { vacancy -> }
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Избранное",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
                        },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,)
            )
            Text(
                text = "${favorites.size} вакансий",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(favorites) { vacancy ->
                    JobCardUtils(
                        vacancy = vacancy,
                        onRespond = { onRespond(vacancy) },
                        onFavoriteToggle = {
                            onFavoriteToggle(vacancy) // Обрабатываем клик на избранное
                        },
                        isFavorite = true
                    )
                }
            }
        }
    )
}
