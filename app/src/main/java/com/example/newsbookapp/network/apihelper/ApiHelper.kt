package com.example.newsbookapp.network.apihelper

import com.example.newsbookapp.network.apiservice.NewsService
import com.example.newsbookapp.API_KEY
import com.example.newsbookapp.COUNTRY
import javax.inject.Inject


class ApiHelper @Inject constructor(private val apiService: NewsService) {

    suspend fun getHeadlines() = apiService.getHeadLines(COUNTRY, API_KEY)

}