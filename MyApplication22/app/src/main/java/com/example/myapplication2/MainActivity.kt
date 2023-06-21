package com.example.myapplication2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnShowToast = findViewById<Button>(R.id.btnShowToast)

        btnShowToast.setOnClickListener {
           Toast(this).apply {
               duration = Toast.LENGTH_LONG
               val customToast = layoutInflater.inflate(R.layout.custom_toast,findViewById(R.id.clToast))
               setGravity(Gravity.CENTER, 0, 0)
               view = customToast
           }.show()
           }
            }
        }



