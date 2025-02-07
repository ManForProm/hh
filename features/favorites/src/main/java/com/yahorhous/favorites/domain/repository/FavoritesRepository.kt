package com.yahorhous.favorites.domain.repository

import com.yahorhous.favorites.domain.model.Vacancy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


interface FavoritesRepository {
    suspend fun toggleFavorite(vacancy: Vacancy)
    fun getFavorites(): Flow<List<Vacancy>>
    fun getFavoritesCount(): Flow<Int>
}