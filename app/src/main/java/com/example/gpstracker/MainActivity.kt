package com.example.gpstracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.gpstracker.ui.theme.GpsTrackerTheme

val TAG = "gpstracker"

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
            Log.d(TAG, "request denied")
            return
        }

        setContent {
            GpsTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        StartButton(::startForegroundService)
                        EndButton(::stopForegroundService)
                    }
                }
            }
        }
    }

    private fun startForegroundService() {
        Log.d(TAG, "start service from button pressed")
        val serviceIntent = Intent(this, MyForegroundService::class.java)

        startService(serviceIntent)
    }

    private fun stopForegroundService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)

        stopService(serviceIntent)
    }
}


@SuppressLint("MissingPermission")
@Composable
fun StartButton(onButtonClick: ()-> Unit) {
    Button(onClick = {
        Log.d(TAG,"start")


        onButtonClick()
    }) {
        Text(text = "start")
    }
}
@Composable
fun EndButton(onButtonClick: ()-> Unit) {
    Button(onClick = {
        Log.d(TAG,"end")


        onButtonClick()
    }) {
        Text(text="end")
    }
}
