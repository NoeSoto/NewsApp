package com.example.newsapp

import com.example.newsapp.API.NewsAPIJson
import retrofit2.http.GET

interface APIRequest {
    @GET("/v2/everything?q=bitcoin&apiKey=72a9f80efb4846fb918a9370d144aed0")
    suspend fun getNews():NewsAPIJson
}