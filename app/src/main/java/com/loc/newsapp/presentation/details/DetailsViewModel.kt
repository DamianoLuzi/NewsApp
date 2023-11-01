package com.loc.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel              //adding HiltViewModel annotation to inject use cases using dependency injection
class DetailsViewModel @Inject constructor(
    private val newsUseCases : NewsUseCases
): ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set                                             // private set so that it can only
                                                                    // be changed from the view model
    fun onEvent(event : DetailsEvent) {
        when(event) {
            is DetailsEvent.UpsertDeleteArticle ->{
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if(article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun upsertArticle(article : Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect = "Article added successfully!"
    }
    private suspend fun deleteArticle(article : Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = "Article deleted"
    }
}


