package com.example.newsbookapp.network.apiservice

import com.example.newsbookapp.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksService {

    @GET("volumes")
    suspend fun getBooks(
            @Query("q") query: String
    ): ApiResponse

}