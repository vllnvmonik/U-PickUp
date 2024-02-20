package com.echelon.upickup.repository

import com.echelon.upickup.model.SignIn
import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.SignInRequest
import com.echelon.upickup.network.apimodel.SignInResponse
import retrofit2.Response

class SignInRepository {
    private val signInApiService = RetrofitInstance.signInApiService

    suspend fun signIn(credentials: SignIn): Response<SignInResponse> {
        val signInRequest = SignInRequest(credentials.idNumber, credentials.password)
        return signInApiService.signIn(signInRequest)
    }
}