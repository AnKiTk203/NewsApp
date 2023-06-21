package com.codingblocks.notification

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notification.databinding.ActivityMain2Binding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMain2Binding
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val context = this
        binding.btnInsert.setOnClickListener {
            if (binding.etName.text.toString().isNotEmpty() &&
                binding.etAge.text.toString().isNotEmpty()
            )
            {
                var user = User(binding.etName.text.toString(),
                    binding.etAge.text.toString().toInt())
                var db = DataBaseHandler(context)
                db.insertData(user)
            }
            else
                Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()
        }

    }
}