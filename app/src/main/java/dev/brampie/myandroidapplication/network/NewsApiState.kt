package dev.brampie.myandroidapplication.network

import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle

sealed interface NewsApiState {
    object Error: NewsApiState
    object Loading: NewsApiState
    object Success: NewsApiState
}

data class NewsListState(val newsList: List<Newsarticle> = listOf())