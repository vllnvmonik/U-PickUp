package com.echelon.upickup.network.apimodel

data class Modules(
    val subject_code: String,
    val subject_name: String,
    val year_level: Int,
    val course: String,
    val available: Int,
    val quantity: Int
)
data class ModulesResponse(
    val results: List<Modules>
)