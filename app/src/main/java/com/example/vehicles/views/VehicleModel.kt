package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicles.R
import com.example.vehicles.adapters.ItemAdapter
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_make.*

@AndroidEntryPoint
class VehicleModel : Fragment(R.layout.fragment_vehicle_model),
    ItemAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var modelList: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: VehicleViewModel by viewModels()
        setUpRecyclerView()

        viewModel.getModelList()
        modelList = viewModel.modelList
        adapter = ItemAdapter((activity as MainActivity).applicationContext, modelList, this)
        recyclerView.adapter = adapter

    }

    private fun setUpRecyclerView() {
        recyclerView = makerRecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager((activity as MainActivity).applicationContext)
    }

    override fun onItemClicked(position: Int) {

    }
}