package com.example.newsbookapp.dashboard.books

import com.example.newsbookapp.network.apihelper.BooksApiHelper
import javax.inject.Inject


class BooksRepository @Inject constructor(private val booksApiHelper: BooksApiHelper) {

    suspend fun getBooks() = booksApiHelper.getBooks()

}