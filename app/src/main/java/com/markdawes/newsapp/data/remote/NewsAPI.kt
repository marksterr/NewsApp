package com.markdawes.newsapp.data.remote

import com.markdawes.newsapp.data.remote.dtos.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String, @Query("apiKey") apiKey: String): NewsResponse
}