package com.example.intent2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btn2 =findViewById<Button>(R.id.btnSecond)
        btn2.setOnClickListener {
        Intent(this,MainActivity::class.java).also{
            startActivity(it)
        }

        }

    }
}