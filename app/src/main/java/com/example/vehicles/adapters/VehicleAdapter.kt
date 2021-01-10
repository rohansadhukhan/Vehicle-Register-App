package com.example.vehicles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicles.R
import com.example.vehicles.database.Vehicle
import kotlinx.android.synthetic.main.vehicle.view.*

class VehicleAdapter(
    val listener: OnSelectVehicle
) : RecyclerView.Adapter<VehicleAdapter.CustomViewHolder>() {

    private var vehicles : List<Vehicle> = ArrayList()

    fun updateVehiclesList(vehicles : List<Vehicle>) {
        this.vehicles = vehicles
        notifyDataSetChanged()
    }

    interface OnSelectVehicle {
        fun onClickVehicle(position: Int)
    }

    class CustomViewHolder(
        itemView: View,
        private val listener: OnSelectVehicle
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClickVehicle(adapterPosition)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder =
        CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vehicle, parent, false),
            listener
        )

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {
        val vehicle = vehicles[position]
        holder.itemView.apply {
            vehicleNumber.text = vehicle.number
            vehicleName.text = vehicle.name
        }

    }

    override fun getItemCount(): Int = vehicles.size
}