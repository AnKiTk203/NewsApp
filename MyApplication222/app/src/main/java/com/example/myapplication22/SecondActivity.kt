package com.example.myapplication22

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val btn1 = findViewById<Button>(R.id.btnBack)
        val btn2 = findViewById<Button>(R.id.btnNext)
        btn1.setOnClickListener () {
            finish()
        }
            btn2.setOnClickListener(){
                Intent(this,ThirdActivity::class.java).also{
                    startActivity(it)
                }
            }

    }
}