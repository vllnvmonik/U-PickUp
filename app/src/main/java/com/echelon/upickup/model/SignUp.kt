package com.echelon.upickup.model

data class SignUp(
    val name: String,
    val middleName: String,
    val lastName: String,
    val program: String,
    val department: String,
    val gender: String,
    val age: String,
    val email:String,
    val idNumber: String,
    val password: String,
    val confirmPassword: String
)
