package com.example.myapplication22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btnOpenActivity)
        btn.setOnClickListener(){
            Intent(this,SecondActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}