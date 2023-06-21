package com.example.combined

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.tbMenu)
        setSupportActionBar(toolbar)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        btnOrder.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName)
            val roll = findViewById<EditText>(R.id.etRoll)
            val age = findViewById<EditText>(R.id.etAge)
            val contact = findViewById<EditText>(R.id.etContact)
            val Name = name.text.toString()
            val Roll = roll.text.toString().toInt()
            val Age = age.text.toString().toInt()
            val Contact = contact.text.toString()
          Intent(this,ThirdActivity::class.java).apply{
              putExtra(ThirdActivity.NAME,Name )
              putExtra(ThirdActivity.AGE,Age)
              putExtra(ThirdActivity.ROLL, Roll)
              putExtra(ThirdActivity.CONTACT,Contact)
              startActivity(intent)
            }
            Intent(this,SecondActivity::class.java).also{
                startActivity(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miAC -> {
                Toast.makeText(this, "Add Contact Selected", Toast.LENGTH_SHORT).show()
            }

            R.id.miHelp -> {
                Toast.makeText(this, "Help Selected", Toast.LENGTH_SHORT).show()
            }

            R.id.miSettings -> {
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show()
            }

            R.id.miFavorites -> {
                Toast.makeText(this, "Favorites Selected", Toast.LENGTH_SHORT).show()
            }

            R.id.miGF -> {
                Toast.makeText(this, "Give Feedback Selected", Toast.LENGTH_SHORT).show()
            }

            R.id.miClose -> {
                finish()
            }
        }
        return true
    }
}

