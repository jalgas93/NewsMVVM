package com.androiddevs.mvvmnewsapp.model

data class NewsResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)