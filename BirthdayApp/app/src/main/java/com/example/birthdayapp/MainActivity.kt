package com.example.birthdayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etName = findViewById<EditText>(R.id.etName)
        val wish = findViewById<Button>(R.id.btn1)
        wish.setOnClickListener{
            val name = etName.editableText.toString()
            Intent(this, MainActivity2::class.java).also {
                it.putExtra("name",name)
                startActivity(it)
            }
        }
    }
}