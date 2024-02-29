package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.Uniform
import com.echelon.upickup.network.apimodel.UniformsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InventoryUniformsApiService {
    @GET("uniforms")
    suspend fun getUniforms(): Response<UniformsResponse>
}

interface FilterUniformApiService {
    @GET("uniforms/{course}/{year_level}")
    suspend fun getUniforms(@Path("course") course: String, @Path("year_level") year_level: Int): Response<List<Uniform>>
}