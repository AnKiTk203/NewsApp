package com.example.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd=findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener{
            val etFirstNumber = findViewById<EditText>(R.id.etFirstNumber)
            val firstNumber = etFirstNumber.text.toString().toInt()
            val etSecondNumber = findViewById<EditText>(R.id.etSecondNumber)
            val secondNumber = etSecondNumber.text.toString().toInt()
            val result = firstNumber + secondNumber
            val etResult = findViewById<TextView>(R.id.etResult)
            etResult.text = result.toString()

        }
    }
}