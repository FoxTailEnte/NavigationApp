package com.example.navigationapp.repository.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Location @Inject constructor(
    private val context: Context,
    private val locationManager: LocationManager,
    private val ioDispatcher: CoroutineDispatcher
) : LocationUseCase {
    private lateinit var locationListener: LocationListener

    override fun getPosition(): Flow<String> = callbackFlow {
        locationListener = LocationListener { location ->
            val latitude = location.latitude
            val longitude = location.longitude
            trySend("$latitude $longitude")
        }
        /*if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {*/
            withContext(Dispatchers.Main) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0L,
                    0f,
                    locationListener
                )
        } /*else {
            *//* val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
             val uri = Uri.fromParts("package", packageName, null)
             intent.data = uri
             startActivity(intent)*//*
        }*/
        awaitClose {
            locationManager.removeUpdates(locationListener)
        }

    }.flowOn(ioDispatcher)

}