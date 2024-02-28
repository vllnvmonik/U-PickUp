package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.BooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface InventoryBooksApiService {
    @GET("books")
    suspend fun getBooks(): Response<BooksResponse>
}

interface FilterBooksApiService {
    @GET("books/{course}/{year_level}")
    suspend fun getBooks(): Response<List<Books>>
}