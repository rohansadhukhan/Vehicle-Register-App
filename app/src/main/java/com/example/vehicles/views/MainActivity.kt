package com.example.vehicles.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.vehicles.R
import com.example.vehicles.viewmodel.VehicleViewModel

class MainActivity : AppCompatActivity() {

//    lateinit var viewModel: VehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
    }

}