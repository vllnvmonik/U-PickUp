package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InventoryBooksApiService {
    @GET("books")
    suspend fun getBooks(): Response<BooksResponse>
}

interface FilterBooksApiService {
    @GET("books/{course}/{year_level}")
    suspend fun getBooks(@Path("course") course: String, @Path("year_level") year_level: Int): Response<List<Books>>
}