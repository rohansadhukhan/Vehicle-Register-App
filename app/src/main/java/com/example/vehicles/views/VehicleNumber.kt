package com.example.vehicles.views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.vehicles.R
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_vehicle_number.*

@AndroidEntryPoint
class VehicleNumber : Fragment(R.layout.fragment_vehicle_number) {

    private lateinit var number: String
    lateinit var viewModel : VehicleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).viewModel

        (activity as MainActivity).toolbar.title = "Create new vehicle profile"

        Log.d("ViewModel Hash Code -", viewModel.hashCode().toString())

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