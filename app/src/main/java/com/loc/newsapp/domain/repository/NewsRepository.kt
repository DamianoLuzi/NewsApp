package com.loc.newsapp.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {          //provides an API for requesting data
    fun getNews(sources: List<String>) : Flow<PagingData<Article>>
    fun searchNews(searchQuery:String, sources: List<String>) : Flow<PagingData<Article>>   //paging data ->wrapper class for data : way to get data from the web faster
}