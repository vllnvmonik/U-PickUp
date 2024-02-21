package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Books
import retrofit2.Response
import retrofit2.http.GET

interface InventoryBooksApiService {
    @GET("books")
    suspend fun getBooks(): Response<List<Books>>
}