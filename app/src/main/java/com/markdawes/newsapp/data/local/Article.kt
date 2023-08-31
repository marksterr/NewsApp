package com.markdawes.newsapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_data_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    val id: Int? = null,
    @ColumnInfo(name = "article_title")
    val title: String,
    @ColumnInfo(name = "article_image")
    val image: String,
    @ColumnInfo(name = "article_content")
    val content: String,
    @ColumnInfo(name = "article_url")
    val url: String
)
