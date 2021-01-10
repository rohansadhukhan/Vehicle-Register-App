package com.example.vehicles.module

import android.content.Context
import androidx.room.Room
import com.example.vehicles.api.ApiService
import com.example.vehicles.api.BASE_URL
import com.example.vehicles.database.VehicleDao
import com.example.vehicles.database.VehicleDatabase
import com.example.vehicles.repository.VehicleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(
    ApplicationComponent::class
)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ) : VehicleDatabase =
        Room.databaseBuilder(
            context,
            VehicleDatabase::class.java,
            "vehicleDatabase"
        ).build()

    @Provides
    @Singleton
    fun providesVehicleDao(
        database: VehicleDatabase
    ) : VehicleDao =
        database.vehicleDao()

    @Provides
    fun provideVehicleRepository(
        vehicleDao: VehicleDao,
        api : ApiService
    ) : VehicleRepository =
        VehicleRepository(vehicleDao, api)

    @Provides
    @Singleton
    fun provideRetrofit() : ApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

}