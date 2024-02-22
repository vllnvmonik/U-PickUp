package com.echelon.upickup.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Event
import com.echelon.upickup.repository.CalendarRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalendarViewModel: ViewModel() {
    private val postRepository = CalendarRepository()

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchEvents() {
        viewModelScope.launch {
            try {
                val response = postRepository.getEvents()
                if (response.isSuccessful) {
                    val details = response.body()
                    details?.let {
                        _events.value = it.map { event ->
                            event.copy(
                                event_date = parseDateTime(event.event_date).toString()
                            )
                        }
                        Log.d("CalendarViewModel", "Fetched events: $details")
                    }
                } else {
                    Log.e("CalendarViewModel", "Failed to fetch events: ${response.code()}")
                    // Handle error response
                }
            } catch (e: Exception) {
                Log.e("CalendarViewModel", "Error fetching events: ${e.message}", e)
                // Handle network exceptions
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseDateTime(dateTimeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(dateTimeString, formatter)
    }
}
