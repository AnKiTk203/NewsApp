package com.example.combined

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ThirdActivity :AppCompatActivity(){

    companion object{
        const val NAME = "EXTRA_NAME"
        const val AGE = "EXTRA_AGE"
        const val ROLL = "EXTRA_ROLL"
        const val CONTACT = "EXTRA_CONTACT"
        const val ORDER = "EXTRA_ORDER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        val tvOrder = findViewById<TextView>(R.id.tvOrder)
        val name = intent.getStringExtra(NAME)
        val age= intent.getIntExtra(AGE,0)
        val roll = intent.getIntExtra(ROLL,0)
        val contact = intent.getStringExtra(CONTACT)
        val order = intent.getStringExtra(ORDER)
        val about = "Mr. $name is $age years old. His Roll No. is $roll and his contact no. is $contact"
        tvInfo.text = about
        tvOrder.text = order.toString()

    }
}