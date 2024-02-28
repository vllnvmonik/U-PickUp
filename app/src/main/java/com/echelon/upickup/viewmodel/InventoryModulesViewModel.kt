package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.ModulesResponse
import com.echelon.upickup.repository.InventoryModulesRepository
import kotlinx.coroutines.launch

class InventoryModulesViewModel: ViewModel() {
    private val inventoryModulesRepository = InventoryModulesRepository()

    private val _modules = MutableLiveData<List<ModulesResponse>>()
    val modules: LiveData<List<ModulesResponse>> = _modules

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchModules() {
        viewModelScope.launch {
            try {
                val response = inventoryModulesRepository.getModules()
                if (response.isSuccessful){
                    val details = response.body()
                    details?.let {
                        _modules.value = listOf(it)
                        Log.d("InventoryModulesViewModel", "Fetched modules: $details")
                    }
                } else {
                    Log.e("InventoryModulesViewModel", "Failed to fetch modules: ${response.code()}")
                    // Handle error response
                }
            } catch (e: Exception) {
                Log.e("InventoryModulesViewModel", "Error fetching modules: ${e.message}", e)
                // Handle network exceptions
            }
        }
    }
}
