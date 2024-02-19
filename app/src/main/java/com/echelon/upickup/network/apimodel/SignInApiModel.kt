package com.echelon.upickup.network.apimodel

// model dapat same sila doon sa column or doon sa need sa api natin
data class SignInRequest(
    val student_id: String,
    val password: String
)

data class SignInResponse(
    val success: Boolean
)
