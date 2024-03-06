package com.echelon.upickup.repository

import com.echelon.upickup.model.SignUp
import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.SignUpRequest
import com.echelon.upickup.network.apimodel.SignUpResponse
import com.echelon.upickup.network.apiservice.SignUpApiService
import retrofit2.Response

class SignUpRepository
//    private val checkStudentIdApiService: CheckStudentID,
//    private val checkStudentEmailApiService: CheckStudentEmail
 {
     private val signUpApiService = RetrofitInstance.signUpApiService

     suspend fun signUp(signUpRequest: SignUp): Response<SignUpResponse> {
        val request = SignUpRequest(
            signUpRequest.name,
            signUpRequest.middleName,
            signUpRequest.lastName,
            signUpRequest.gender,
            signUpRequest.age,
            signUpRequest.program,
            signUpRequest.department,
            signUpRequest.email,
            signUpRequest.idNumber,
            signUpRequest.password,
            signUpRequest.confirmPassword
            )
        return signUpApiService.signUp(request)
    }

//    suspend fun checkStudentId(email: String): Response<CheckIdResponse> {
//        return checkStudentIdApiService.checkStudentID(email)
//    }
//
//    suspend fun checkStudentEmail(email: String): Response<CheckEmailResponse> {
//        return checkStudentEmailApiService.checkStudentEmail(email)
//    }
}