package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.repository.InventoryBooksRepository
import kotlinx.coroutines.launch

class InventoryBooksViewModel: ViewModel() {
    private val inventoryBooksRepository = InventoryBooksRepository()

    private val _books = MutableLiveData<List<Books>>()
    val books: LiveData<List<Books>> = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val response = inventoryBooksRepository.getBooks()
                if (response.isSuccessful){
                    val details = response.body()
                    _books.value = details
                    Log.d("InventoryBooksViewModel", "Fetched books: $details")
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
}
