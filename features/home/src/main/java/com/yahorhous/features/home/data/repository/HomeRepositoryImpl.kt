package com.yahorhous.features.home.data.repository

import com.yahorhous.core.network.api.ApiService
import com.yahorhous.core.network.model.Offers
import com.yahorhous.core.network.model.Vacancy
//import com.yahorhous.favorites.domain.model.Vacancy
import com.yahorhous.features.home.domain.models.ButtonData
import com.yahorhous.features.home.domain.models.Recommendation
import com.yahorhous.features.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val apiService: ApiService
) : HomeRepository {


    override suspend fun getVacancies(): List<Vacancy> {
        val response = apiService.getMockJson()
        if (response.isSuccessful) {
            return response.body()?.vacancies ?: emptyList()
        } else {
            throw Exception("Ошибка: ${response.code()}")
        }
    }

    override suspend fun getRecommendations(): List<Offers> {
        val response = apiService.getMockJson()
        if (response.isSuccessful) {
            return response.body()?.offers ?: emptyList()
        } else {
            throw Exception("Ошибка: ${response.code()}")
        }
    }
}