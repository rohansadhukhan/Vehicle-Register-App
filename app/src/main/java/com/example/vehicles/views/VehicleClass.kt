package com.example.vehicles.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.vehicles.R
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_class.*
import kotlinx.android.synthetic.main.item.view.*

@AndroidEntryPoint
class VehicleClass : Fragment(R.layout.fragment_vehicle_class) {

    lateinit var viewModel : VehicleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).actionBar?.title ?: "Select Class"

        car.itemName.text = "Car"
        bike.itemName.text = "Bike"

        viewModel = (activity as MainActivity).viewModel
//        Log.d("ViewModel Hash Code -", viewModel.hashCode().toString())

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