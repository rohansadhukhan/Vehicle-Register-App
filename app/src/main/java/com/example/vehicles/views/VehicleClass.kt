package com.example.vehicles.views

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.vehicles.R
import com.example.vehicles.viewmodel.VehicleViewModel
import kotlinx.android.synthetic.main.fragment_vehicle_class.*
import kotlinx.android.synthetic.main.item.view.*

class VehicleClass : Fragment(R.layout.fragment_vehicle_class) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        car.itemName.text = "Car"
        bike.itemName.text = "Bike"

        val viewModel : VehicleViewModel by viewModels()

        car.setOnClickListener {
            viewModel.vehicleType = "4w"
            Navigation.findNavController(view).navigate(R.id.action_vehicleClass_to_vehicleMake)
        }

        bike.setOnClickListener {
            viewModel.vehicleType = "2w"
            Navigation.findNavController(view).navigate(R.id.action_vehicleClass_to_vehicleMake)
        }
    }
}