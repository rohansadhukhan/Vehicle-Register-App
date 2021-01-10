package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vehicles.R
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_transmission.*
import kotlinx.android.synthetic.main.item.view.*

@AndroidEntryPoint
class VehicleTransmission : Fragment(R.layout.fragment_vehicle_transmission) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manualTransmission.itemName.text = "Manual"
        automaticTransmission.itemName.text = "Automatic"

        val viewModel: VehicleViewModel by viewModels()

        manualTransmission.setOnClickListener {
            viewModel.vehicleTransmission = "Manual"
            findNavController().navigate(R.id.action_vehicleTransmission_to_vehicleProfile)
        }

        automaticTransmission.setOnClickListener {
            viewModel.vehicleTransmission = "Automatic"
            findNavController().navigate(R.id.action_vehicleTransmission_to_vehicleProfile)
        }

    }
}