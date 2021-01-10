package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.vehicles.R
import com.example.vehicles.database.Vehicle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_vehicle_profile.*
import kotlinx.android.synthetic.main.profile_item.view.*

@AndroidEntryPoint
class VehicleProfile : Fragment(R.layout.fragment_vehicle_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).toolbar.title = "Profile"

        val args : VehicleProfileArgs by navArgs()
        val vehicle : Vehicle = args.vehicle

        vehicle.apply {
            vehicleName.text = name
            vehicleNumber.text = number
            profileMake.itemProperty.text = "MAKE"
            profileMake.itemValue.text = make
            profileModel.itemProperty.text = "MODEL"
            profileModel.itemValue.text = model
            profileFuel.itemProperty.text = "FUEL TYPE"
            profileFuel.itemValue.text = fuel
            profileTransmission.itemProperty.text = "TRANSMISSION"
            profileTransmission.itemValue.text = transmission
        }

    }
}