package com.loc.newsapp.data.manager.remote.dto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)