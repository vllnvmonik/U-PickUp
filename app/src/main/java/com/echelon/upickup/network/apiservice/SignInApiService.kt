package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.SignInRequest
import com.echelon.upickup.network.apimodel.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApiService {
    // put the route of the api here ex. student-login or student-registration ;>
    @POST("student-login")
    suspend fun signIn(@Body signInRequest: SignInRequest): Response<SignInResponse>
}