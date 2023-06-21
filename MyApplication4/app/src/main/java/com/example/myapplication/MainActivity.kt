package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnRequest = findViewById<Button>(R.id.btnRequest)
        btnRequest.setOnClickListener {
            requestPermissions()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasReadMediaAudio() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)==PackageManager.PERMISSION_GRANTED
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasReadMediaImages() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)==PackageManager.PERMISSION_GRANTED
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasReadMediaVideo() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO)==PackageManager.PERMISSION_GRANTED
    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun hasLocationBackgroundPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)==PackageManager.PERMISSION_GRANTED

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun requestPermissions() {
        val permissionToRequest = mutableListOf<String>()
        if (!hasReadMediaAudio()) {
            permissionToRequest.add(Manifest.permission.READ_MEDIA_AUDIO)
        }
        if (!hasReadMediaImages()) {
            permissionToRequest.add(Manifest.permission.READ_MEDIA_IMAGES)
        }
        if (!hasReadMediaVideo()) {
            permissionToRequest.add(Manifest.permission.READ_MEDIA_VIDEO)
        }
        if (!hasLocationForegroundPermission()) {
            permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasLocationBackgroundPermission() && hasLocationForegroundPermission()) {
            permissionToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (permissionToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PermissionsRequest", "${permissions[i]} granted")
                }
            }
        }
    }
    }

