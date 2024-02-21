package com.echelon.upickup.model

data class SignIn(
    val idNumber: String,
    val password: String
)
data class StudentDetails(
    val id: String,
    val studentId: String,
    val email: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val department: String,
    val program: String
)