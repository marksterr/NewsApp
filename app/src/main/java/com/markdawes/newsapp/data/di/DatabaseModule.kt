package com.markdawes.newsapp.data.di

import android.content.Context
import androidx.room.Room
import com.markdawes.newsapp.data.local.ArticleDAO
import com.markdawes.newsapp.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        return Room.databaseBuilder(
            appContext,
            ArticleDatabase::class.java,
            "article_data_database"
        ).build()
    }

    @Provides
    fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDAO {
        return articleDatabase.articleDAO
    }
}