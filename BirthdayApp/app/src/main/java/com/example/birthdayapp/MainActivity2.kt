package com.example.birthdayapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val Name = intent.getStringExtra("name")
        val wish = findViewById<TextView>(R.id.tvWish)
        wish.text = "Happy Birthday\n$Name!"
    }
}