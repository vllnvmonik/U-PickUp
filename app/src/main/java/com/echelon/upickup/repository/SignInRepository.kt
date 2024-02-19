package com.echelon.upickup.repository

import com.echelon.upickup.model.SignIn
import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.SignInRequest
import com.echelon.upickup.network.apimodel.SignInResponse

class SignInRepository {
    private val signInApiService = RetrofitInstance.signInApiService

    suspend fun signIn(credentials: SignIn): SignInResponse? {
        val signInRequest = SignInRequest(credentials.idNumber, credentials.password)
        val response = signInApiService.signIn(signInRequest)
        return if (response.isSuccessful) response.body() else null
    }
}