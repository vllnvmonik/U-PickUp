package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Uniform
import retrofit2.Response
import retrofit2.http.GET

interface InventoryUniformsApiService {
    @GET("uniforms")
    suspend fun getUniforms(): Response<List<Uniform>>
}