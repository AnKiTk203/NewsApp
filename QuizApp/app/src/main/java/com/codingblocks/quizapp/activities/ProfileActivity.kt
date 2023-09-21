package com.codingblocks.quizapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firebaseAuth = FirebaseAuth.getInstance()
        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        txtEmail.text = firebaseAuth.currentUser?.email
        val btnSignOut = findViewById<Button>(R.id.btnLogOut)
        btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val it = Intent(this, Login::class.java)
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
            startActivity(it)
            finish()
        }
    }
}