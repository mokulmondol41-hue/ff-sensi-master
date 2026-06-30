package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.DeviceRepository
import com.example.data.DeviceSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensitivityViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedDevice = MutableStateFlow<DeviceSettings?>(null)
    val selectedDevice: StateFlow<DeviceSettings?> = _selectedDevice.asStateFlow()

    private val _selectedBrandFilter = MutableStateFlow<String?>(null)
    val selectedBrandFilter: StateFlow<String?> = _selectedBrandFilter.asStateFlow()

    private val _filteredDevices = MutableStateFlow<List<DeviceSettings>>(DeviceRepository.getAllDevices())
    val filteredDevices: StateFlow<List<DeviceSettings>> = _filteredDevices.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterDevices()
    }

    fun selectBrandFilter(brand: String?) {
        _selectedBrandFilter.value = brand
        filterDevices()
    }

    fun selectDevice(device: DeviceSettings?) {
        _selectedDevice.value = device
    }

    private fun filterDevices() {
        val query = _searchQuery.value
        val brand = _selectedBrandFilter.value

        var results = if (query.trim().isEmpty()) {
            DeviceRepository.getAllDevices()
        } else {
            DeviceRepository.searchDevices(query)
        }

        if (brand != null) {
            results = results.filter { it.brand.equals(brand, ignoreCase = true) }
            // If results are empty but user explicitly searched for something, respect their search
            if (results.isEmpty() && query.trim().isNotEmpty()) {
                val customDev = DeviceRepository.generateDeterministicDevice(query)
                if (customDev.brand.equals(brand, ignoreCase = true)) {
                    results = listOf(customDev)
                }
            }
        }

        _filteredDevices.value = results
    }
}
