package com.yahorhous.favorites.domain.repository

import com.yahorhous.core.network.model.Vacancy
import kotlinx.coroutines.flow.Flow


interface FavoritesRepository {
    suspend fun toggleFavorite(vacancy: Vacancy)
    fun getFavorites(): Flow<List<Vacancy>>
    fun addFavorite(vacancy: Vacancy)
    fun removeFavorite(vacancyId: String)

}