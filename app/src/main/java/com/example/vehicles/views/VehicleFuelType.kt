package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicles.R
import com.example.vehicles.adapters.ItemAdapter
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_make.*

@AndroidEntryPoint
class VehicleFuelType : Fragment(R.layout.fragment_vehicle_fuel_type),
    ItemAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private var fuelTypeList: ArrayList<String> = ArrayList()

    lateinit var viewModel : VehicleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        fuelTypeList.add("Petrol")
        fuelTypeList.add("Diesel")
        fuelTypeList.add("CNG")
        fuelTypeList.add("Petrol + CNG")
        fuelTypeList.add("Electric")
        fuelTypeList.add("Hybrid")

//        adapter = ItemAdapter((activity as MainActivity).applicationContext, fuelTypeList, this)
//        recyclerView.adapter = adapter

    }

    private fun setUpRecyclerView() {
        recyclerView = makerRecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager((activity as MainActivity).applicationContext)
    }

    override fun onItemClicked(position: Int) {
        viewModel.vehicleFuel = fuelTypeList[position]
        findNavController().navigate(R.id.action_vehicleFuelType_to_vehicleTransmission)
    }

}