package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.StudentDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentDetailsApiService {
    @GET("students/{id}")
    suspend fun studentDetails(@Path("id") id: Int): Response<StudentDetailsResponse>
}
