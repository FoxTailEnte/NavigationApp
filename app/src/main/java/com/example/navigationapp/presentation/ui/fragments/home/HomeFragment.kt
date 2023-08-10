package com.example.navigationapp.presentation.ui.fragments.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.navigationapp.databinding.FragmentMainBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment
import com.example.navigationapp.presentation.ui.fragments.result.ResultFragment
import com.example.navigationapp.presentation.ui.main.MainActivity
import com.example.navigationapp.repository.utils.setContentFragment


class HomeFragment : BaseFragment<FragmentMainBinding>() {
    private val vm: HomeViewModel by viewModels({ requireActivity() }) { HomeViewModelFactory() }
    private lateinit var locationManager: LocationManager
    private val locationListener: LocationListener = LocationListener { location ->
        val latitude = location.latitude
        val longitude = location.longitude
        Log.d("MyLog", "$latitude $longitude")
    }


    override fun initializeBinding(): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    override fun setupListener() = with(binding) {
        btResult.setOnClickListener {
            requireActivity().setContentFragment(ResultFragment())
        }
        btStart.setOnClickListener {
            getPosition()
        }
    }

    private fun getPosition() {
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0f,
                locationListener
            )
        } else {
            val activity = activity as MainActivity
            activity.getPermission()
        }
    }
}