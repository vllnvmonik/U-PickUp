package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.ModulesResponse
import retrofit2.Response

class InventoryModulesRepository {
    private val inventoryModulesApiService = RetrofitInstance.inventoryModulesApiService

    suspend fun getModules(): Response<ModulesResponse> {
        return inventoryModulesApiService.getModules()
    }
}