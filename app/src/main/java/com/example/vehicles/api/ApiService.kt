package com.example.vehicles.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL: String = "https://test.turbocare.app/turbo/care/v1/"

interface ApiService {

    @GET("makes")
    suspend fun getVehicleMakers(
        @Query("class") vehicleClass: String
    ): Response<List<String>>

    @GET("models")
    suspend fun getVehicleModel(
        @Query("class") vehicleClass: String,
        @Query("make") vehicleMake: String
    ): Response<List<String>>

}