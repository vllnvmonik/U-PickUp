package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Modules
import retrofit2.Response
import retrofit2.http.GET

interface InventoryModulesApiService {
    @GET("modules")
    suspend fun getModules(): Response<List<Modules>>
}