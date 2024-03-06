package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.CheckEmailResponse
import com.echelon.upickup.network.apimodel.CheckIdResponse
import com.echelon.upickup.network.apimodel.SignUpRequest
import com.echelon.upickup.network.apimodel.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignUpApiService {
    @POST("student-registration")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>
}

interface CheckStudentID {
    @GET("student-registration/{student_id}")
    suspend fun checkStudentID(
    @Path("student_id") email: String
    ): Response<CheckIdResponse>
}

interface CheckStudentEmail {
//    @GET("student-registration/{email_ad}")
//    suspend fun getEmail(): Response<CheckEmailResponse>
    @GET("student-registration/{email_ad}")
    suspend fun checkStudentEmail(
    @Path("email_ad") email: String
    ): Response<CheckEmailResponse>
}