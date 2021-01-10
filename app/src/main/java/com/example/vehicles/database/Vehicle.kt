package com.example.vehicles.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "vehicles"
)
data class Vehicle(
    val name: String,
    var number: String,
    val type: String,
    val make: String,
    val model: String,
    val fuel: String,
    val transmission: String
) {
    @PrimaryKey(
        autoGenerate = true
    )
    var id: Int? = null
}