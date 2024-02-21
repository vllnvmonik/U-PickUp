package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.repository.InventoryModulesRepository
import kotlinx.coroutines.launch

class InventoryModulesViewModel: ViewModel() {
    private val inventoryModulesRepository = InventoryModulesRepository()

    private val _modules = MutableLiveData<List<Modules>>()
    val modules: LiveData<List<Modules>> = _modules

    fun fetchModules() {
        viewModelScope.launch {
            try {
                val response = inventoryModulesRepository.getModules()
                if (response.isSuccessful){
                    val details = response.body()
                    _modules.value = details
                    Log.d("InventoryModulesViewModel", "Fetched modules: $details")
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
