package com.yahorhous.favorites.domain.usecase

import com.yahorhous.favorites.domain.model.Vacancy
import com.yahorhous.favorites.domain.repository.FavoritesRepository

class ToggleFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(vacancy: Vacancy) {
        repository.toggleFavorite(vacancy)
    }
}