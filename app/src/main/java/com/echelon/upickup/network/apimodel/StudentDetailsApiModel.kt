package com.echelon.upickup.network.apimodel


data class StudentDetailsResponse(
    val student: StudentDetails
)

data class StudentDetails(
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