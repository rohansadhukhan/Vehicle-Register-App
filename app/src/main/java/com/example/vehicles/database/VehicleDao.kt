package com.example.vehicles.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewVehicle(vehicle : Vehicle)

    @Delete
    fun deleteVehicle(vehicle : Vehicle)

    @Query("SELECT * FROM vehicles")
    fun getAllVehicles() : LiveData<List<Vehicle>>

}