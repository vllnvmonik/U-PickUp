package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.StudentDetailsResponse
import retrofit2.Response

class StudentRepository {
    private val studentDetailsApiService = RetrofitInstance.studentDetailsApiService

    suspend fun getStudentDetails(id: Int): Response<StudentDetailsResponse> {
        return studentDetailsApiService.studentDetails(id)
    }
}