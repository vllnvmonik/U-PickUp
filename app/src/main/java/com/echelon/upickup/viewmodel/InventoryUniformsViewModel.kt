package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.Uniform
import com.echelon.upickup.network.apimodel.UniformsResponse
import com.echelon.upickup.repository.InventoryUniformsRepository
import com.echelon.upickup.sharedprefs.ModulesManager
import com.echelon.upickup.sharedprefs.UniformsManager
import kotlinx.coroutines.launch

class InventoryUniformsViewModel: ViewModel() {
    private val inventoryUniformsRepository = InventoryUniformsRepository()

    private val _uniforms = MutableLiveData<List<UniformsResponse>>()
    val uniform: LiveData<List<UniformsResponse>> = _uniforms

    private val _getYear = MutableLiveData<List<Uniform>>()
    val getYear: LiveData<List<Uniform>> = _getYear

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchUniforms() {
        viewModelScope.launch {
            try {
                val response = inventoryUniformsRepository.getUniforms()
                if (response.isSuccessful){
                    val details = response.body()
                    details?.let {
                        _uniforms.value = listOf(it)
                        Log.d("InventoryUniformsViewModel", "Fetched uniforms: $details")
                    }
                    UniformsManager.saveUniformsResponse(details)
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

    fun fetchUniformsByYr(course: String, year_level: Int) {
        viewModelScope.launch {
            try {
                val response = inventoryUniformsRepository.getUniformsYr(course, year_level)
                if (response.isSuccessful){
                    val details = response.body()
                    details?.let {
                        _getYear.value = it
                        Log.d("InventoryUniformsViewModel", "Fetched unifs: $details")
                    }
                    UniformsManager.saveUniformsByYear(details)
                } else {
                    Log.e("getbyyearrunif", "Failed tsdsfsfo sdfsdf unif:${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("sdfsdfsdfsdunif", "Error sdfsd dssdfsdfs: ${e.message}", e)
            }
        }
    }
}
