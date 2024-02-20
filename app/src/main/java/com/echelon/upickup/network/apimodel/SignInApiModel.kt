package com.echelon.upickup.network.apimodel

// model dapat same sila doon sa column or doon sa need sa api natin
data class SignInRequest(
    val student_id: String,
    val password: String
)
data class SignInResponse(
    val message: String,
    val token: String,
    val data: StudentData
)
data class StudentData(
    val id: Int,
    val student_id: String,
    val email_ad: String,
    val first_name: String,
    val middle_name: String,
    val last_name: String,
    val age: Int,
    val gender: String,
    val department: String,
    val program: String
    )
