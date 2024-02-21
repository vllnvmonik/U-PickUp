package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.ForgotPasswordRequest
import com.echelon.upickup.network.apimodel.ForgotPasswordResponse

class ForgotPasswordRepository {
    private val service = RetrofitInstance.forgotPasswordApiService

    suspend fun requestPasswordReset(email: String): ForgotPasswordResponse? {
        val request = ForgotPasswordRequest(email)
        val response = service.requestPasswordReset(request)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}