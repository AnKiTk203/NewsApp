package com.codingblocks.listviews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lvFruits = findViewById<ListView>(R.id.lvFruits)
        lvFruits.adapter = ArrayAdapter(
            this,
              R.layout.list_view_fruit,
              R.id.tvFruitName,
              arrayOf(
                      "Apple","Mangoes","Guavas","Litchis","Melons","Grapes","Kiwis","Apples",
                      "Mangoes","Guavas","Litchis","Melons","Grapes","Kiwis"))
        lvFruits.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this@MainActivity,
                "Johnny Ate ${position+1} ${view.findViewById<TextView>(R.id.tvFruitName).text}",
                Toast.LENGTH_SHORT).show()
        }
    }
}