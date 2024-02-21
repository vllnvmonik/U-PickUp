package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.ForgotPasswordRequest
import com.echelon.upickup.network.apimodel.ForgotPasswordResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordApiService {
    @POST("forgot-password")
    suspend fun requestPasswordReset(@Body request: ForgotPasswordRequest): Response<ForgotPasswordResponse>
}