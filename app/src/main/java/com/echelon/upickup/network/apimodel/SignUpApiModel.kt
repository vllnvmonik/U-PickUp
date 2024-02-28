package com.echelon.upickup.network.apimodel

data class StudentRegistration(
    val student_id: String,
    val email_ad: String,
    val first_name: String,
    val middle_name: String,
    val last_name: String,
    val age: Int,
    val gender: String,
    val department: String,
    val program: String,
    val password: String,
    val password_confirmation: String
)

data class SignUpRequest(
    val student_id: String,
    val email_ad: String,
    val first_name: String,
    val middle_name: String,
    val last_name: String,
    val age: Int,
    val gender: String,
    val department: String,
    val program: String,
    val password: String,
    val password_confirmation: String
)

data class SignUpResponse(
    val message: String
)

data class CheckIdResponse(
    val message: String
)

data class CheckEmailResponse(
    val message: String
)