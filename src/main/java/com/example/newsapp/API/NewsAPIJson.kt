package com.example.newsapp.API

data class NewsAPIJson(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)