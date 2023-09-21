package com.codingblocks.todoapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.todoapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        binding.tvHello.text= "I am Ankit"
        CoroutineScope(Dispatchers.IO).launch {
            printFollowers()
        }
    }

    private suspend fun printFollowers() {


         CoroutineScope(Dispatchers.IO).launch {
            val insta =async { getInstaFollowers() }
             val fb = async {getFbFollowers()}
             Log.d(TAG,"FB - ${fb.await()} INSTA - ${insta.await()}")
        }
    }
    private suspend fun getInstaFollowers(): Int {
       delay(1000)
        return 113
    }
    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 54
    }
}