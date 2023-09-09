package com.codingblocks.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.quizapp.databinding.ActivityIntroLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginIntro : AppCompatActivity() {
    private lateinit var binding: ActivityIntroLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val auth = FirebaseAuth.getInstance()
         if(auth.currentUser != null){
             Toast.makeText(this, "User is already logged in",Toast.LENGTH_SHORT).show()
             redirect("MAIN")
         }

        binding.btnGetStarted.setOnClickListener{
            redirect("LOGIN")
        }

    }
    private fun redirect (name: String){
        val intent = when(name){
            "LOGIN" -> Intent(this, Login::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}