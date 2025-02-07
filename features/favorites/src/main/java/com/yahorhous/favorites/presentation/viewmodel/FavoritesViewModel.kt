package com.yahorhous.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahorhous.core.network.model.Vacancy
import com.yahorhous.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel() {

    val favoritesDetails: Flow<List<Vacancy>> = repository.getFavorites()
    val favoritesCount: Flow<Int> = favoritesDetails.map { it.size }

    // Функция для переключения состояния "избранного"
    fun toggleFavorite(vacancy: Vacancy) {
        viewModelScope.launch {
            repository.toggleFavorite(vacancy)
        }
    }
}
