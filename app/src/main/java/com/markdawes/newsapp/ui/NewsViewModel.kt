package com.markdawes.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markdawes.newsapp.data.NewsRepo
import com.markdawes.newsapp.data.local.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repo: NewsRepo) : ViewModel() {
    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> get() = _articles

    init {
        getArticles()
    }

    private fun getArticles() = viewModelScope.launch {
        _articles.value = repo.getArticles()
    }

    fun refreshArticles() = viewModelScope.launch {
        _articles.value = repo.refreshArticles()
    }
}