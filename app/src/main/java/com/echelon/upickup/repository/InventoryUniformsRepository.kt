package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.Uniform
import com.echelon.upickup.network.apimodel.UniformsResponse
import retrofit2.Response

class InventoryUniformsRepository {
    private val inventoryUniformsApiService = RetrofitInstance.inventoryUniformsApiService
    private val filterUniformApiService = RetrofitInstance.filterUniformApiService

    suspend fun getUniforms(): Response<UniformsResponse> {
        return inventoryUniformsApiService.getUniforms()
    }
    suspend fun getUniformsYr(course: String, year_level: Int): Response<List<Uniform>> {
        return filterUniformApiService.getUniforms(course, year_level)
    }
}