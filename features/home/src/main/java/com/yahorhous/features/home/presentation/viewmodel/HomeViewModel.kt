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
            _favorites.value = favoritesRepository.getFavorites().toSet()
        }
    }

    fun toggleFavorite(vacancyId: String) {
        viewModelScope.launch {
            val updatedFavorites = _favorites.value.toMutableSet()
            if (updatedFavorites.contains(vacancyId)) {
                favoritesRepository.removeFavorite(vacancyId)
                updatedFavorites.remove(vacancyId)
            } else {
                favoritesRepository.addFavorite(vacancyId)
                updatedFavorites.add(vacancyId)
            }
            _favorites.value = updatedFavorites
        }
    }
}
