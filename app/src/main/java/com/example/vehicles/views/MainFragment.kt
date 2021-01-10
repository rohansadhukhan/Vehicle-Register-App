package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicles.R
import com.example.vehicles.adapters.VehicleAdapter
import com.example.vehicles.database.Vehicle
import com.example.vehicles.viewmodel.VehicleViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main),
    VehicleAdapter.OnSelectVehicle {

    private lateinit var vehicleList: List<Vehicle>
    lateinit var viewModel: VehicleViewModel

    private lateinit var vehicleAdapter: VehicleAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).actionBar?.title ?: "Vehicles"

        setUpRecyclerView()
        viewModel = (activity as MainActivity).viewModel

        viewModel.getAllVehicles.observe(viewLifecycleOwner, Observer {
            vehicleList = it
            vehicleAdapter.updateVehiclesList(it)
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val vehicle = vehicleList[position]
                viewModel.delete(vehicle)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("UNDO") {
                        viewModel.insert(vehicle)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(vehicleRecyclerView)
        }

        registerNewVehicle.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_vehicleNumber)
        }
    }

    private fun setUpRecyclerView() {
        vehicleAdapter = VehicleAdapter(this)
        vehicleRecyclerView.apply {
            layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            adapter = vehicleAdapter
        }
    }

    override fun onClickVehicle(position: Int) {
        val bundle: Bundle = Bundle().apply {
            putSerializable("vehicle", vehicleList[position])
        }
        findNavController().navigate(
            R.id.action_mainFragment_to_vehicleProfile,
            bundle
        )
    }

}