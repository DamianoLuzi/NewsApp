package com.loc.newsapp.di

import android.app.Application
import androidx.room.Room
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.local.NewsDatabase
import com.loc.newsapp.data.local.NewsTypeConverter
import com.loc.newsapp.data.manager.LocalUserManagerImplementation
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.useCases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.useCases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.useCases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.useCases.news.DeleteArticle
import com.loc.newsapp.domain.useCases.news.GetNews
import com.loc.newsapp.domain.useCases.news.NewsUseCases
import com.loc.newsapp.domain.useCases.news.SearchNews
import com.loc.newsapp.domain.useCases.news.SelectArticle
import com.loc.newsapp.domain.useCases.news.SelectArticles
import com.loc.newsapp.domain.useCases.news.UpsertArticle
import com.loc.newsapp.util.Constants.BASE_URL
import com.loc.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application : Application
    ): LocalUserManager = LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
      localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideUseApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao : NewsDao
    ) : NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()       //Room migrates the database for you
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase : NewsDatabase     //Dagger Hilt will inject the provided Database in here
    ): NewsDao = newsDatabase.newsDao
}