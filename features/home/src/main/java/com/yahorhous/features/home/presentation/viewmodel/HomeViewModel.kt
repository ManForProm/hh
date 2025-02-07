package com.yahorhous.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahorhous.core.network.model.Offers
import com.yahorhous.core.network.model.Vacancy
import com.yahorhous.favorites.domain.repository.FavoritesRepository
import com.yahorhous.features.home.domain.models.Recommendation
import com.yahorhous.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies: StateFlow<List<Vacancy>> = _vacancies.asStateFlow()

    private val _recommendations = MutableStateFlow<List<Offers>>(emptyList())
    val recommendations: StateFlow<List<Offers>> = _recommendations.asStateFlow()


    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    private val _choosenFilter = MutableStateFlow("filter")
    val choosenFilter: StateFlow<String> = _choosenFilter.asStateFlow()

    fun updateFilter(newFilter: String) {
        _choosenFilter.value = newFilter
    }

    init {
        viewModelScope.launch {
            _vacancies.value = repository.getVacancies()
            _recommendations.value = repository.getRecommendations()
            _favorites.value = favoritesRepository.getFavorites().map { it.map { vacancy -> vacancy.id }.toSet() }
        }
    }

    fun toggleFavorite(vacancy: Vacancy) {
        viewModelScope.launch {
            val updatedFavorites = _favorites.value.toMutableSet()
            if (updatedFavorites.contains(vacancy.id)) {
                favoritesRepository.removeFavorite(vacancy.id)
                updatedFavorites.remove(vacancy.id)
            } else {
                favoritesRepository.addFavorite(vacancy)
                updatedFavorites.add(vacancy.id)
            }
            _favorites.value = updatedFavorites
        }
    }
}
