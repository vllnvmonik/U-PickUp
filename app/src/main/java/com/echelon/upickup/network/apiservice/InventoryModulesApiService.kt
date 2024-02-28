package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.ModulesResponse
import retrofit2.Response
import retrofit2.http.GET

interface InventoryModulesApiService {
    @GET("modules")
    suspend fun getModules(): Response<ModulesResponse>
}

interface FilterModulesApiService {
    @GET("modules/{course}/{year_level}")
    suspend fun getModules(): Response<List<Modules>>
}
