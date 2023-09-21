package com.example.intent2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et = findViewById<EditText>(R.id.editTextText)
        val btnMail = findViewById<Button>(R.id.btnMail)
        val btnBrowse = findViewById<Button>(R.id.btnBrowse)
        val btnDial = findViewById<Button>(R.id.btnDial)
val next = findViewById<Button>(R.id.btnNew)
        btnMail.setOnClickListener {
            val mail = et.editableText.toString()
            val go = Intent()
            go.action = Intent.ACTION_SENDTO
            go.data = Uri.parse("mailto:$mail")
            go.putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")
            startActivity(go)
        }
        btnBrowse.setOnClickListener {
            val address = et.editableText.toString()
            val xyz = Intent()
            xyz.action = Intent.ACTION_VIEW
            xyz.data = Uri.parse("http://www.$address.com")
            startActivity(xyz)
        }
        btnDial.setOnClickListener {
            val dial = et.editableText.toString()
            val i = Intent()
            i.action = Intent.ACTION_DIAL
            i.data = Uri.parse("tel:$dial")
            startActivity(i)
        }
        next.setOnClickListener {
            Intent(this,MainActivity2::class.java).also{
                startActivity(it)
            }

        }
    }
}