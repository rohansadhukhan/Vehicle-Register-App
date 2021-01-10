package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehicles.R
import com.example.vehicles.adapters.ItemAdapter
import com.example.vehicles.database.Vehicle
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_vehicle_make.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var vehicleList: List<Vehicle>
    lateinit var viewModel : VehicleViewModel

//    private lateinit var vAdapter: ItemAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).viewModel

//        viewModel.getAllVehicles.observe(viewLifecycleOwner, Observer {
//            vehicleList = it
//        })

        registerNewVehicle.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_vehicleNumber)
        }
    }

//    private fun setUpRecyclerView() {
//        makerAdapter = ItemAdapter(this)
//        makerRecyclerView.apply {
//            this.setHasFixedSize(true)
//            layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
//            makerAdapter.updateItemList(listOf("Rohan"))
//            adapter = makerAdapter
//        }
//    }

}