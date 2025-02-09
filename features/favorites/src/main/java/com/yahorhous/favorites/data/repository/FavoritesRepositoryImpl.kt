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

    private val vacancyListType = Types.newParameterizedType(List::class.java, Vacancy::class.java)
    private val vacancyListAdapter = moshi.adapter<List<Vacancy>>(vacancyListType)

    private val _favoritesFlow = MutableStateFlow<List<Vacancy>>(emptyList())

    // Получение списка избранных вакансий
    override fun getFavorites(): StateFlow<List<Vacancy>> {
        val json = prefs.getString("favorites", "[]") ?: "[]"
        val favorites = try {
            vacancyListAdapter.fromJson(json) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
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

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == "favorites") {
            val json = prefs.getString("favorites", "[]") ?: "[]"
            _favoritesFlow.value = parseFavorites(json)
        }
    }

    init {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    private fun parseFavorites(json: String): List<Vacancy> {
        return try {
            (vacancyListAdapter.fromJson(json)) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}