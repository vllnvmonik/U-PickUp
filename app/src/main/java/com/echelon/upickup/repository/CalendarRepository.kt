package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Event
import retrofit2.Response

class CalendarRepository {
    private val calendarApiService = RetrofitInstance.calendarApiService

    suspend fun getEvents(): Response<List<Event>> {
        return calendarApiService.getEvents()
    }
}