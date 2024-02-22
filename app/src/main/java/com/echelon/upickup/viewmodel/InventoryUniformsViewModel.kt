package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Uniform
import com.echelon.upickup.repository.InventoryUniformsRepository
import kotlinx.coroutines.launch

class InventoryUniformsViewModel: ViewModel() {
    private val inventoryUniformsRepository = InventoryUniformsRepository()

    private val _uniforms = MutableLiveData<List<Uniform>>()
    val uniform: LiveData<List<Uniform>> = _uniforms

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchUniforms() {
        viewModelScope.launch {
            try {
                val response = inventoryUniformsRepository.getUniforms()
                if (response.isSuccessful){
                    val details = response.body()
                    _uniforms.value = details
                    Log.d("InventoryUniformsViewModel", "Fetched uniforms: $details")
                } else {
                    Log.e("InventoryUniformsViewModel", "Failed to fetch uniforms: ${response.code()}")
                    // Handle error response
                }
            } catch (e: Exception) {
                Log.e("InventoryUniformsViewModel", "Error fetching uniforms: ${e.message}", e)
                // Handle network exceptions
            }
        }
    }
}
