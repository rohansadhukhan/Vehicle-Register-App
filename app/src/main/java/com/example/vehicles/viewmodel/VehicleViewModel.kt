package com.example.vehicles.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehicles.database.Vehicle
import com.example.vehicles.repository.VehicleRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class VehicleViewModel
@ViewModelInject
constructor(val vehicleRepository: VehicleRepository) : ViewModel() {

    var vehicleNumber: String? = null
    var vehicleType: String? = null
    var vehicleMake: String? = null
    var vehicleModel: String? = null
    var vehicleFuel: String? = null
    var vehicleTransmission: String? = null

    var makerList: MutableLiveData<List<String>> = MutableLiveData()
    var modelList: MutableLiveData<List<String>> = MutableLiveData()

    init {
        getMakerList()
        getModelList()
    }

    val getAllVehicles: LiveData<List<Vehicle>> = vehicleRepository.getAllVehicles

    fun insert(vehicle: Vehicle) = viewModelScope.launch {
        vehicleRepository.insertNewVehicle(vehicle)
    }

    private fun getMakerList() = viewModelScope.launch {
        if(vehicleType != null) {
            val response = vehicleRepository.api.getVehicleMakers(vehicleType!!)
            if (response.isSuccessful) {
                makerList.postValue(response.body())
                Log.d("View Model Check", "maker list done")
            } else {
                Log.d("View Model Check", response.message())
            }
        } else {
            Log.d("View Model Check", "Vehicle type is null")
        }
    }

    fun getModelList() = viewModelScope.launch {
        if(vehicleType != null && vehicleMake != null) {
            val response = vehicleRepository.api.getVehicleModel(vehicleType!!, vehicleMake!!)
            if (response.isSuccessful) {
                modelList.postValue(response.body())
            } else {
                Log.d("View Model Check", response.message())
            }
        } else {
            Log.d("View Model Check", "Vehicle type is null")
        }
    }

}