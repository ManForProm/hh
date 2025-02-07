package com.yahorhous.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.yahorhous.favorites.domain.model.Vacancy
import com.yahorhous.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel() {

    val favoritesCount: Flow<Int> = repository.getFavoritesCount()
    val favoritesDetails: Flow<List<Vacancy>> = repository.getFavorites()

}
