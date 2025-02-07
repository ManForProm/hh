package com.yahorhous.core.di

import com.yahorhous.core.network.api.ApiService
import com.yahorhous.core.network.api.moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    // Создаем OkHttpClient с логированием
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    // Создаем Retrofit с базовым URL
    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    // Создаем бин для ApiService
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}