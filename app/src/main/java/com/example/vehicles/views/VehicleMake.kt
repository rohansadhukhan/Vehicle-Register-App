package com.example.vehicles.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehicles.R
import com.example.vehicles.adapters.ItemAdapter
import com.example.vehicles.viewmodel.VehicleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_vehicle_make.*

@AndroidEntryPoint
class VehicleMake : Fragment(R.layout.fragment_vehicle_make), ItemAdapter.OnItemClickListener {

    private lateinit var makerAdapter: ItemAdapter

    lateinit var viewModel: VehicleViewModel
    var makerList: List<String> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).actionBar?.title ?: "Select Make"

        setUpRecyclerView()
        viewModel = (activity as MainActivity).viewModel

//        Log.d("ViewModel Hash Code ", viewModel.hashCode().toString())

        viewModel.getMakerList()
        viewModel.makerList.observe(viewLifecycleOwner, Observer {
            makerList = it
            makerProgressBar.visibility = View.INVISIBLE
            makerAdapter.updateItemList(it)
        })

    }

    private fun setUpRecyclerView() {
        makerAdapter = ItemAdapter(this)
        makerRecyclerView.apply {
            this.setHasFixedSize(true)
            layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            adapter = makerAdapter
        }
        makerProgressBar.visibility = View.VISIBLE
    }

    override fun onItemClicked(position: Int) {
        viewModel.vehicleMake = makerList[position]
        findNavController().navigate(R.id.action_vehicleMake_to_vehicleModel)
    }
}