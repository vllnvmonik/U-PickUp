package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.BooksResponse
import retrofit2.Response

class InventoryBooksRepository {
    private val inventoryBooksApiService = RetrofitInstance.inventoryBooksApiService
    private val filterBooksApiService = RetrofitInstance.filterBooksApiService
    suspend fun getBooks(): Response<BooksResponse> {
        return inventoryBooksApiService.getBooks()
    }
    suspend fun getBooksYr(course: String, year_level: Int): Response<List<Books>> {
        return filterBooksApiService.getBooks(course, year_level)
    }
}