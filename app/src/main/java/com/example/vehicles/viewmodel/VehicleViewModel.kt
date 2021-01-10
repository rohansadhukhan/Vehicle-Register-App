package com.example.vehicles.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehicles.database.Vehicle
import com.example.vehicles.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehicleViewModel
@ViewModelInject
constructor(private val vehicleRepository: VehicleRepository) : ViewModel() {

    var vehicleNumber: String? = null
    var vehicleType: String? = null
    var vehicleMake: String? = null
    var vehicleModel: String? = null
    var vehicleFuel: String? = null
    var vehicleTransmission: String? = null

    var makerList: List<String> = ArrayList()
    var modelList: List<String> = ArrayList()

    val getAllVehicles: LiveData<List<Vehicle>> = vehicleRepository.getAllVehicles

    fun insert(vehicle: Vehicle) = viewModelScope.launch {
        vehicleRepository.insertNewVehicle(vehicle)
    }

    fun getMakerList() = viewModelScope.launch {
        val response = vehicleMake?.let { vehicleRepository.getVehicleMakers(it) }
        if (response?.body() != null) {
            makerList = response.body()!!
        }
    }

    fun getModelList() = viewModelScope.launch {
        val response = vehicleModel?.let {
            vehicleType?.let { it1 ->
                vehicleMake?.let { it2 ->
                    vehicleRepository.getVehicleModel(
                        it1, it2
                    )
                }
            }
        }
        if (response != null) {
            if (response.body() != null) {
                modelList = response.body()!!
            }
        }
    }

}