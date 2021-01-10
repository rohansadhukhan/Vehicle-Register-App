package com.example.vehicles.views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicles.R
import com.example.vehicles.adapters.ItemAdapter
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_make.*

@AndroidEntryPoint
class VehicleMake : Fragment(R.layout.fragment_vehicle_make), ItemAdapter.OnItemClickListener {

    private lateinit var makerAdapter: ItemAdapter

    lateinit var viewModel : VehicleViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        viewModel = (activity as MainActivity).viewModel

//        Log.d("ViewModel Hash Code ", viewModel.hashCode().toString())

        viewModel.makerList.observe(viewLifecycleOwner, Observer {
            makerAdapter.updateItemList(it)
        })

    }

    private fun setUpRecyclerView() {
        makerAdapter = ItemAdapter(this)
        makerRecyclerView.apply {
            this.setHasFixedSize(true)
            layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            makerAdapter.updateItemList(listOf("Rohan"))
            adapter = makerAdapter
        }

    }

    override fun onItemClicked(position: Int) {
//        TODO("Not yet implemented")
    }
}