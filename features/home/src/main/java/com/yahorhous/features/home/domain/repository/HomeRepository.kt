package com.yahorhous.features.home.domain.repository

import com.yahorhous.core.network.model.Offers
import com.yahorhous.core.network.model.Vacancy
import com.yahorhous.features.home.domain.models.Recommendation

interface HomeRepository {
    suspend fun getVacancies(): List<Vacancy>
    suspend fun getRecommendations(): List<Offers>
}