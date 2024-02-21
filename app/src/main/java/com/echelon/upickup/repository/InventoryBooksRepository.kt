package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Books
import retrofit2.Response

class InventoryBooksRepository {
    private val inventoryBooksApiService = RetrofitInstance.inventoryBooksApiService

    suspend fun getBooks(): Response<List<Books>> {
        return inventoryBooksApiService.getBooks()
    }
}