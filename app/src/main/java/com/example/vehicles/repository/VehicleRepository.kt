package com.example.vehicles.repository

import androidx.lifecycle.LiveData
import com.example.vehicles.api.ApiService
import com.example.vehicles.database.Vehicle
import com.example.vehicles.database.VehicleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    val vehicleDao: VehicleDao,
    val api : ApiService
) {

    val getAllVehicles : LiveData<List<Vehicle>> = vehicleDao.getAllVehicles()

    suspend fun insertNewVehicle(vehicle: Vehicle) = withContext(Dispatchers.IO) {
        vehicleDao.insertNewVehicle(vehicle)
    }

    suspend fun deleteVehicle(vehicle: Vehicle) = withContext(Dispatchers.IO) {
        vehicleDao.deleteVehicle(vehicle)
    }

    suspend fun getVehicleMakers(type : String)  =
        api.getVehicleMakers(type)

    suspend fun getVehicleModel(type : String, make : String)  =
        api.getVehicleModel(type, make)

}