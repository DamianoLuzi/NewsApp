package com.loc.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.loc.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 1,)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {    //Room implements this class inside AppModule
    abstract val newsDao : NewsDao
}