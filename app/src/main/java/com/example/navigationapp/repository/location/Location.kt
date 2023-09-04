package com.example.navigationapp.repository.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.example.navigationapp.repository.db.LocationEntity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class Location @Inject constructor(
    private val context: Context,
) : LocationUseCase {
    private lateinit var locationCallback: LocationCallback
    private val locationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    override fun checkPermission(): Flow<Boolean> = flow {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            emit(true)
        } else {
            emit(false)
        }
    }

     @SuppressLint("MissingPermission", "VisibleForTests")
     fun getLoc(): Flow<LocationEntity> = callbackFlow {
         val locationRequest = LocationRequest()
         locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
         locationRequest.interval = 5000
         locationRequest.fastestInterval = 5000
         locationRequest.smallestDisplacement = 0f

         locationCallback = object : LocationCallback() {
             override fun onLocationResult(locationResult: LocationResult?) {
                 locationResult?.lastLocation?.let { location ->
                     val latitude = location.latitude.toString()
                     val longitude = location.longitude.toString()
                     val date = convertDate(System.currentTimeMillis())
                     val entity =
                         LocationEntity(latitude = latitude, longitude = longitude, date = date)
                     trySend(entity)
                 }
             }
         }

         withContext(Dispatchers.Main) {
             locationProviderClient.requestLocationUpdates(
                 locationRequest,
                 locationCallback,
                 Looper.getMainLooper()
             )
         }
         awaitClose {
             locationProviderClient.removeLocationUpdates(locationCallback)
         }
     }.flowOn(Dispatchers.IO)

    override fun stopGettingLocation() {
        locationProviderClient.removeLocationUpdates(locationCallback)
    }

    private fun convertDate(time: Long): String {
        val date = java.util.Date(time)
        val dateFormat = java.text.SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return dateFormat.format(date)
    }
}