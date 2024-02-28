package com.echelon.upickup.network.apimodel

data class Books(
    val subject_name: String,
    val year_level: Int,
    val course: String,
    val available: Int,
    val quantity: Int
)
data class BooksResponse(
    val results: List<Books>
)