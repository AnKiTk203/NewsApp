package com.codingblocks.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fruits = Fruit.genRandomFruits(100)
        val fruitAdapter= FruitAdapter(fruits)
                val rvFruit= findViewById<RecyclerView>(R.id.rvFruits)
       rvFruit.layoutManager = LinearLayoutManager(this)
        rvFruit.adapter = fruitAdapter
    }
}