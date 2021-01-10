package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicles.R
import com.example.vehicles.adapters.ItemAdapter
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_make.*
import kotlinx.android.synthetic.main.fragment_vehicle_model.*

@AndroidEntryPoint
class VehicleModel : Fragment(R.layout.fragment_vehicle_model),
    ItemAdapter.OnItemClickListener {

    lateinit var viewModel : VehicleViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var modelAdapter: ItemAdapter
    private var modelList: List<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).actionBar?.title ?: "Select Model"

        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        viewModel.getModelList()
        viewModel.modelList.observe(viewLifecycleOwner, Observer {
            modelList = it
            modelProgressBar.visibility = View.INVISIBLE
            modelAdapter.updateItemList(it)
        })

    }

    private fun setUpRecyclerView() {
        modelAdapter = ItemAdapter(this)
        modelRecyclerView.apply {
            this.setHasFixedSize(true)
            layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            adapter = modelAdapter
        }
        modelProgressBar.visibility = View.VISIBLE
    }

    override fun onItemClicked(position: Int) {
        viewModel.vehicleModel = modelList[position]
        findNavController().navigate(R.id.action_vehicleModel_to_vehicleFuelType)
    }
}