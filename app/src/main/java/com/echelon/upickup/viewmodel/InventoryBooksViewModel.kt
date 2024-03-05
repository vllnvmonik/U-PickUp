package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.BooksResponse
import com.echelon.upickup.repository.InventoryBooksRepository
import com.echelon.upickup.sharedprefs.BooksManager
import kotlinx.coroutines.launch

class InventoryBooksViewModel: ViewModel() {
    private val inventoryBooksRepository = InventoryBooksRepository()

    private val _books = MutableLiveData<List<BooksResponse>>()
    val books: LiveData<List<BooksResponse>> = _books

    private val _getYear = MutableLiveData<List<Books>>()
    val getYear: LiveData<List<Books>> = _getYear

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val response = inventoryBooksRepository.getBooks()
                if (response.isSuccessful){
                    val details = response.body()
                    details?.let {
                        _books.value = listOf(it)
                        Log.d("InventoryBooksViewModel", "Fetched books: $details")
                    }
                } else {
                    Log.e("InventoryBooksViewModel", "Failed to fetch books: ${response.code()}")
                    // Handle error response
                }
            } catch (e: Exception) {
                Log.e("InventoryBooksViewModel", "Error fetching books: ${e.message}", e)
                // Handle network exceptions
            }
        }
    }
    fun fetchBooksByYr(course: String, year_level: Int) {
        viewModelScope.launch {
            try {
                val response = inventoryBooksRepository.getBooksYr(course, year_level)
                if (response.isSuccessful){
                    val details = response.body()
                    details?.let {
                        _getYear.value = it
                        Log.d("getbyyearr", "Fetchesdfsdfd books: $details")
                    }
                } else {
                    Log.e("getbyyearr", "Failed tsdsfsfo sdfsdf books:${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("sdfsdfsdfsd", "Error sdfsd dssdfsdfs: ${e.message}", e)
            }
        }
    }


}
