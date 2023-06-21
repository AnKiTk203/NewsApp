package com.example.myapplication2


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var counter = 0
        val count = findViewById<Button>(R.id.btnCount)

        count.setOnClickListener {
            counter++
            val tvcount = findViewById<TextView>(R.id.tvCount)
            tvcount.text = "Let's Count Together: $counter"
        }
    }
}