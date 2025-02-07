package com.yahorhous.favorites.data.repository

import com.yahorhous.favorites.domain.model.Vacancy
import com.yahorhous.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl : FavoritesRepository {

    private val _favoritesFlow = MutableStateFlow<List<Vacancy>>(listOf(
        Vacancy(
            id = 1,
            viewers = 7,
            title = "Дизайнер для маркетплейсов Wildberries, Ozon",
            salary = "1500-2900 Br",
            location = "Минск",
            category = "Еком дизайн",
            experience = "Опыт от 1 года до 3 лет",
            datePublished = "16 февраля"
        ),
        Vacancy(
            id = 2,
            viewers = 5,
            title = "UI/UX дизайнер для мобильных приложений",
            salary = "2000-3500 Br",
            location = "Минск",
            category = "Мобильный дизайн",
            experience = "Опыт от 2 до 5 лет",
            datePublished = "10 февраля"
        )))

    override fun getFavorites(): Flow<List<Vacancy>> = _favoritesFlow

    override fun getFavoritesCount(): Flow<Int> = _favoritesFlow.map { it.size }

    override suspend fun toggleFavorite(vacancy: Vacancy) {
        // Если вакансия уже добавлена в избранное — удаляем, иначе добавляем
        val currentFavorites = _favoritesFlow.value.toMutableList()
        val index = currentFavorites.indexOfFirst { it.id == vacancy.id }
        if (index >= 0) {
            currentFavorites.removeAt(index)
        } else {
            // Добавляем вакансию и отмечаем ее как избранную
            currentFavorites.add(vacancy.copy(isFavorite = true))
        }
        _favoritesFlow.value = currentFavorites
    }
}