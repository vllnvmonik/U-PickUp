package com.echelon.upickup.network.apimodel

data class Uniform(
    val uniform_type: String,
    val year_level: Int,
    val course: String,
    val available: Int,
    val quantity: Int
)
data class UniformsResponse(
    val results: List<Uniform>
)
