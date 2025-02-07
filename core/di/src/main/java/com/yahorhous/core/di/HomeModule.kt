package com.yahorhous.core.di

import com.yahorhous.features.home.data.repository.HomeRepositoryImpl
import com.yahorhous.features.home.domain.repository.HomeRepository
import com.yahorhous.features.home.presentation.viewmodel.HomeViewModel
import org.koin.core.module.dsl.*
import org.koin.dsl.module

val homeModule = module {
//    factory { GetVacanciesUseCase(get()) }
    viewModel { HomeViewModel(get(),get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}