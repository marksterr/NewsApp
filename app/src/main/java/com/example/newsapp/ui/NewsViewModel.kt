package com.example.newsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.NewsRepo
import com.example.newsapp.data.local.Article
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