package com.example.gpstracker

import android.location.Location
import android.location.LocationListener
import android.util.Log

class MyLocationListener : LocationListener{
    override fun onLocationChanged(p0: Location) {
        val latitude = p0.latitude
        val longitude = p0.longitude
        Log.d(TAG, "Latitude: $latitude, Longitude: $longitude")
    }
}