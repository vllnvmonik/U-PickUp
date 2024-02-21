package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Event
import com.echelon.upickup.repository.CalendarRepository
import kotlinx.coroutines.launch

class CalendarViewModel: ViewModel() {
    private val postRepository = CalendarRepository()

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    fun fetchEvents() {
        viewModelScope.launch {
            try {
                val response = postRepository.getEvents()
                if (response.isSuccessful){
                    val details = response.body()
                    _events.value = details
                    Log.d("CalendarViewModel", "Fetched events: $details")
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
}
