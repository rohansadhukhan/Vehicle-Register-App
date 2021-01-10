package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.vehicles.R
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_number.*

@AndroidEntryPoint
class VehicleNumber : Fragment(R.layout.fragment_vehicle_number) {

    private lateinit var number: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : VehicleViewModel by viewModels()

        goToMake.setOnClickListener {
            number = inputVehicleNumber.text.toString()
            if (validateNumber(number)) {
                viewModel.vehicleNumber = number
                Navigation.findNavController(view)
                    .navigate(R.id.action_vehicleNumber_to_vehicleClass)
            }
        }

    }

    private fun validateNumber(number: String): Boolean {
        if (number.isEmpty()) {
            inputVehicleNumber.error = "Enter vehicle number"
            return false
        }
        return true
    }

}