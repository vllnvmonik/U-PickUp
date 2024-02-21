package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.LogoutResponse
import retrofit2.Response

class LogoutRepository {
    private val logoutApiService = RetrofitInstance.logoutApiService

    suspend fun logout(token: String): Response<LogoutResponse> {
        return logoutApiService.logout("Bearer $token")
    }
}