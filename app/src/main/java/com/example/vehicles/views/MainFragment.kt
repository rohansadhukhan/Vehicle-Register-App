package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.vehicles.R
import com.example.vehicles.database.Vehicle
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var vehicleList: List<Vehicle>
    val viewModel: VehicleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        viewModel.getAllVehicles.observe(viewLifecycleOwner, Observer {
//            vehicleList = it
//        })

        registerNewVehicle.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_vehicleNumber)
        }
    }

}