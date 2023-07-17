package com.example.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDAO : ArticleDAO

    companion object{
        @Volatile
        private var INSTANCE : ArticleDatabase? = null
        fun getInstance(context: Context):ArticleDatabase{
            synchronized(lock = this){
                var instance: ArticleDatabase? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        "article_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}