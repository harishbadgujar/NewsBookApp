package com.example.newsbookapp.network.apiservice

import com.example.newsbookapp.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET("top-headlines")
    suspend fun getHeadLines(
        @Query(COUNTRY) country: String,
        @Query(API_KEY) apKey: String
    ): ApiResponse


    companion object {
        const val API_KEY = "apiKey"
        const val COUNTRY = "country"
        const val PAGE = "page"
    }
}
