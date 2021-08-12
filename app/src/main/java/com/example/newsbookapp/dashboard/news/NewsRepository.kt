package com.example.newsbookapp.dashboard.news

import com.example.newsbookapp.network.apihelper.ApiHelper
import javax.inject.Inject


class NewsRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getHeadlines() = apiHelper.getHeadlines()

}