package com.example.newsapp.data.di

import com.example.newsapp.data.remote.NewsAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val mediaType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    private fun providesJson(): Json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(providesJson().asConverterFactory(mediaType))
            .build()
    }

    @Provides
    fun providesAPIService(retrofit: Retrofit): NewsAPI = retrofit.create()
}