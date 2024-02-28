package com.echelon.upickup.repository

import com.echelon.upickup.network.apimodel.CheckEmailResponse
import com.echelon.upickup.network.apimodel.CheckIdResponse
import com.echelon.upickup.network.apimodel.SignUpRequest
import com.echelon.upickup.network.apimodel.SignUpResponse
import com.echelon.upickup.network.apiservice.CheckStudentEmail
import com.echelon.upickup.network.apiservice.CheckStudentID
import com.echelon.upickup.network.apiservice.SignUpApiService
import retrofit2.Response

class SignUpRepository(
    private val signUpApiService: SignUpApiService,
    private val checkStudentIdApiService: CheckStudentID,
    private val checkStudentEmailApiService: CheckStudentEmail
) {
    suspend fun signIn(signUpRequest: SignUpRequest): Response<SignUpResponse> {
        return signUpApiService.signIn(signUpRequest)
    }

    suspend fun checkStudentId(email: String): Response<CheckIdResponse> {
        return checkStudentIdApiService.checkStudentID(email)
    }

    suspend fun checkStudentEmail(email: String): Response<CheckEmailResponse> {
        return checkStudentEmailApiService.checkStudentEmail(email)
    }
}
