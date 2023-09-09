package com.codingblocks.quizapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.quizapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvSignUp.setOnClickListener{
            Intent(this, SignupActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
        binding.btnLogin2.setOnClickListener{
            loginUser()
        }
    }


    @SuppressLint("SuspiciousIndentation")
    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isBlank()) {
            Toast.makeText(this, "Enter a email address", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isBlank()) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
            }
            else {
                Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
