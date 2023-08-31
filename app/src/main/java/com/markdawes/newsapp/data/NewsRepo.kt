package com.markdawes.newsapp.data

import com.markdawes.newsapp.data.local.Article
import com.markdawes.newsapp.data.local.ArticleDAO
import com.markdawes.newsapp.data.remote.NewsAPI
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val service: NewsAPI,
    private val dao: ArticleDAO
) {

    suspend fun getArticles(): List<Article> {
        val cachedArticles = getCachedArticles()
        return if (cachedArticles.isNotEmpty()) {
            // If the database is not empty, return the cached articles
            cachedArticles
        } else {
            // If the database is empty, fetch from the API and save the fetched articles
            fetchAndSaveArticles()
        }
    }

    suspend fun refreshArticles(): List<Article> {
        // Fetch from the API and save the fetched articles, then return them
        return fetchAndSaveArticles()
    }

    private suspend fun fetchAndSaveArticles(): List<Article> {
        val articleDTOs = service.getTopHeadlines("us", "7ef60d3f5f934fffb311a73676a3b1c5").articles
        val articles = articleDTOs.mapNotNull { dto ->
            if (dto.content != null) {
                Article(
                    title = dto.title!!,
                    image = dto.urlToImage ?: "https://www.bistatedev.org/wp-content/themes/cardinal/images/default-thumb.png",
                    content = dto.content,
                    url = dto.url!!
                )
            } else {
                null
            }
        }
        saveArticles(articles)
        return articles
    }

    suspend fun getCachedArticles(): List<Article> {
        return dao.getArticles().first()
    }

    suspend fun saveArticles(articles: List<Article>) {
        dao.clearArticles() // Clear the database before saving new articles
        dao.insertArticles(articles)
    }
}