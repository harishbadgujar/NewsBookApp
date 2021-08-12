package com.example.newsbookapp.network

import com.example.newsbookapp.model.BookModel
import com.example.newsbookapp.model.NewsModel
import com.squareup.moshi.Json


class ApiResponse {

    @field:Json(name = "status")
    val status: String = ""

    @field:Json(name = "totalResults")
    val totalResults: Int = 0

    @field:Json(name = "articles")
    val articles: List<NewsModel>? = null

    @field:Json(name = "items")
    val books: List<BookModel>? = null

}