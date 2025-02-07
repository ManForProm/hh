package com.yahorhous.core.di

import com.yahorhous.favorites.domain.repository.FavoritesRepository
import com.yahorhous.favorites.data.repository.FavoritesRepositoryImpl
import com.yahorhous.favorites.domain.usecase.ToggleFavoriteUseCase
import com.yahorhous.favorites.presentation.viewmodel.FavoritesViewModel
import org.koin.core.module.dsl.*
import org.koin.dsl.module

val favoritesModule = module {
    factory { ToggleFavoriteUseCase(get()) }
    viewModel { FavoritesViewModel(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl() }
}