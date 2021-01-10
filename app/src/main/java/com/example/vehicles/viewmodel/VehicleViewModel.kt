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
import java.lang.Exception

class VehicleViewModel
@ViewModelInject
constructor(val vehicleRepository: VehicleRepository) : ViewModel() {

    lateinit var vehicleNumber: String
    lateinit var vehicleType: String
    lateinit var vehicleMake: String
    lateinit var vehicleModel: String
    lateinit var vehicleFuel: String
    lateinit var vehicleTransmission: String

    var makerList: MutableLiveData<List<String>> = MutableLiveData()
    var modelList: MutableLiveData<List<String>> = MutableLiveData()

    val getAllVehicles: LiveData<List<Vehicle>> = vehicleRepository.getAllVehicles

    fun insert(vehicle: Vehicle) = viewModelScope.launch {
        vehicleRepository.insertNewVehicle(vehicle)
    }

    fun insertNewVehicle() {
        val vehicle = Vehicle(vehicleModel + vehicleMake, vehicleNumber, vehicleType, vehicleMake, vehicleModel, vehicleFuel, vehicleTransmission)
        insert(vehicle)
    }

    fun getMakerList() = viewModelScope.launch {
        Log.d("rohan", "Enter Maker")
        try {
            val response = vehicleRepository.api.getVehicleMakers(vehicleType!!)
            if (response.isSuccessful) {
                makerList.postValue(response.body())
                Log.d("rohan", "maker list done")
            } else {
                Log.d("rohan", response.message())
            }
        } catch (e : Exception) {
            Log.d("rohan", "Connection Time OUT ${e.message}")
        }
    }

    fun getModelList() = viewModelScope.launch {
        try {
            val response = vehicleRepository.api.getVehicleModel(vehicleType!!, vehicleMake!!)
            if (response.isSuccessful) {
                modelList.postValue(response.body())
            } else {
                Log.d("rohan", response.message())
            }
        } catch (e : Exception) {
            Log.d("rohan", "Connection Time OUT ${e.message}")
        }
    }

}