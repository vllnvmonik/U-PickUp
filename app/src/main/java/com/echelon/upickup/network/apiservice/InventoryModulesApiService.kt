package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.ModulesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InventoryModulesApiService {
    @GET("modules")
    suspend fun getModules(): Response<ModulesResponse>
}

interface FilterModulesApiService {
    @GET("modules/{course}/{year_level}")
    suspend fun getModules(@Path("course") course: String, @Path("year_level") year_level: Int): Response<List<Modules>>
}
