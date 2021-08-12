package com.example.newsbookapp.dashboard.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newsbookapp.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    fun getHeadlines() = liveData {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = newsRepository.getHeadlines()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }

}