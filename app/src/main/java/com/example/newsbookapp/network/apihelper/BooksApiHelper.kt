package com.example.newsbookapp.network.apihelper

import com.example.newsbookapp.network.apiservice.BooksService
import javax.inject.Inject


class BooksApiHelper @Inject constructor(private val booksService: BooksService) {

    suspend fun getBooks() = booksService.getBooks("sport")

}