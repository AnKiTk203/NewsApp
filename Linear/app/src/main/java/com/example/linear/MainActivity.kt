package com.example.linear

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val btn = findViewById<Button>(R.id.btnImage)
        btn.setOnClickListener{
            imageView.setImageResource(R.drawable.character_themuppets_kermit_b77a431b)
        }
    }

}