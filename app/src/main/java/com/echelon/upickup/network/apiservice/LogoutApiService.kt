package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.LogoutResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface LogoutApiService {
    @POST("logout")
    suspend fun logout(@Header("Authorization") token: String): Response<LogoutResponse>
}