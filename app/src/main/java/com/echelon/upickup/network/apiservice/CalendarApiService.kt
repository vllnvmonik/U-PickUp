package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Event
import retrofit2.Response
import retrofit2.http.GET

interface CalendarApiService {
    @GET("events")
    suspend fun getEvents(): Response<List<Event>>
}