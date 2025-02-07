package com.yahorhous.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahorhous.core.network.model.Vacancy
import com.yahorhous.features.home.domain.models.Recommendation
import com.yahorhous.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {
    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies: StateFlow<List<Vacancy>> = _vacancies.asStateFlow()

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _choosenFilter = MutableStateFlow("filter")
    val choosenFilter: StateFlow<String> = _choosenFilter.asStateFlow()

    fun updateFilter(newFilter: String) {
        _choosenFilter.value = newFilter
    }

    init {
        viewModelScope.launch {
            _vacancies.value = repository.getVacancies()
            _recommendations.value = repository.getRecommendations()
        }
    }
}
