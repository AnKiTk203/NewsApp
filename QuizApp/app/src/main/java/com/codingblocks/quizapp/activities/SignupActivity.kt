package com.codingblocks.quizapp.activities




import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.quizapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 9001
    }

    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        if (currentUser != null) {
            // The user is already signed in, navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // finish the current activity to prevent the user from coming back to the SignInActivity using the back button
        }
        binding.tvLogin.setOnClickListener {
            Intent(this, Login::class.java).also {
                startActivity(it)
                finish()
            }
        }
        binding.btnLogin2.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.editableText.toString()
        val confirmPassword = binding.etPassword2.editableText.toString()

        if (email.isBlank()) {
            Toast.makeText(this, "Enter a email address", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmPassword){
            Toast.makeText(this, "Both password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
    }
}}
