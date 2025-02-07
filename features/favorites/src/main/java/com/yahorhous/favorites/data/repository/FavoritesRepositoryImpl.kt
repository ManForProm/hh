package com.yahorhous.favorites.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.yahorhous.core.network.api.moshi
import com.yahorhous.favorites.domain.repository.FavoritesRepository
import com.squareup.moshi.Types
import com.yahorhous.core.network.model.Vacancy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoritesRepositoryImpl(context: Context) : FavoritesRepository {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)

    // Создаем адаптер для списка вакансий
    private val jsonAdapter = moshi.adapter(List::class.java)

    // Мы будем хранить список избранных вакансий как StateFlow
    private val _favoritesFlow = MutableStateFlow<List<Vacancy>>(emptyList())

    // Получение списка избранных вакансий
    override fun getFavorites(): StateFlow<List<Vacancy>> {
        val json = prefs.getString("favorites", "[]") ?: "[]"
        val type = Types.newParameterizedType(List::class.java, Vacancy::class.java)
        val jsonAdapter = moshi.adapter<List<Vacancy>>(type)

        val favorites = try {
            jsonAdapter.fromJson(json) ?: emptyList()
        } catch (e: Exception) {
            emptyList<Vacancy>()
        }

        // Обновляем значение в StateFlow
        _favoritesFlow.value = favorites

        return _favoritesFlow
    }

    override fun addFavorite(vacancy: Vacancy) {
        val favorites = _favoritesFlow.value.toMutableList()
        favorites.add(vacancy)
        saveFavorites(favorites)
    }

    override fun removeFavorite(vacancyId: String) {
        val favorites = _favoritesFlow.value.toMutableList()
        favorites.removeAll { it.id == vacancyId }
        saveFavorites(favorites)
    }

    private fun saveFavorites(favorites: List<Vacancy>) {
        val json = moshi.adapter(List::class.java).toJson(favorites)
        prefs.edit().putString("favorites", json).apply()
    }

    override suspend fun toggleFavorite(vacancy: Vacancy) {
        val currentFavorites = _favoritesFlow.value.toMutableList()
        val index = currentFavorites.indexOfFirst { it.id == vacancy.id }
        if (index >= 0) {
            currentFavorites.removeAt(index)
        } else {
            currentFavorites.add(vacancy)
        }
        _favoritesFlow.value = currentFavorites
    }
}