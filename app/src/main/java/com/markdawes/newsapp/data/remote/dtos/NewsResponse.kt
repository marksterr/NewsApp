package com.markdawes.newsapp.data.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("articles")
    val articles: List<ArticleDTO>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
)