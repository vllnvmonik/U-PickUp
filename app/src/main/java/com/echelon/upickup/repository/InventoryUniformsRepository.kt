package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Uniform
import com.echelon.upickup.network.apimodel.UniformsResponse
import retrofit2.Response

class InventoryUniformsRepository {
    private val inventoryUniformsApiService = RetrofitInstance.inventoryUniformsApiService

    suspend fun getUniforms(): Response<UniformsResponse> {
        return inventoryUniformsApiService.getUniforms()
    }
}