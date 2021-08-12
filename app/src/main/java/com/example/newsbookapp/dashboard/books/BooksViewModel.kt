package com.example.newsbookapp.dashboard.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newsbookapp.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val booksRepository: BooksRepository) : ViewModel() {

    fun getBooks() = liveData {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = booksRepository.getBooks()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }

}