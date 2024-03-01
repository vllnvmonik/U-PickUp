package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.ModulesResponse
import retrofit2.Response

class InventoryModulesRepository {
    private val inventoryModulesApiService = RetrofitInstance.inventoryModulesApiService
    private val filterModulesApiService = RetrofitInstance.filterModulesApiService

    suspend fun getModules(): Response<ModulesResponse> {
        return inventoryModulesApiService.getModules()
    }
    suspend fun getModulesYr(course: String, year_level: Int): Response<List<Modules>> {
        return filterModulesApiService.getModules(course, year_level)
    }
}