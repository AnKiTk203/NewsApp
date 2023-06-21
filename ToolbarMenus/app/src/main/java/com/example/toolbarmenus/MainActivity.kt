package com.example.toolbarmenus

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.tbMenu)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.miAddContact -> {
                Toast.makeText(this, "You Clicked On Add Contact", Toast.LENGTH_SHORT).show()
            }
            R.id.miFavorites -> {
                Toast.makeText(this, "You Clicked On Favorites", Toast.LENGTH_SHORT).show()

            }
            R.id.miFeedback -> {
                Toast.makeText(this, "You Clicked On Feedback", Toast.LENGTH_SHORT).show()
            }
            R.id.miSettings -> {
                Toast.makeText(this, "You Clicked On Settings", Toast.LENGTH_SHORT).show()

            }
            R.id.miClose -> {
                finish()
            }
        }
            return true
        }
    }
