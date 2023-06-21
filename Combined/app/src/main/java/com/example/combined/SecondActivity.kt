package com.example.combined

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val btnDone = findViewById<Button>(R.id.btnDone)

                btnDone.setOnClickListener {
                    val tv1 = findViewById<TextView>(R.id.tv1)
                    val tvAdd = findViewById<TextView>(R.id.tvAdd)
                    val rg = findViewById<RadioGroup>(R.id.rg1)
                    val rbPizza = findViewById<RadioButton>(R.id.rbPizza)
                    val rbSandwich = findViewById<RadioButton>(R.id.rbSandwich)
                    val rbBurger = findViewById<RadioButton>(R.id.rbBurger)
                    val cbCheese = findViewById<CheckBox>(R.id.cbCheese)
                    val cbOnion = findViewById<CheckBox>(R.id.cbOnion)
                    val cbSalad = findViewById<CheckBox>(R.id.cbSalad)
                    val item1 = rg.checkedRadioButtonId
                    val item = findViewById<RadioButton>(item1)
                    val option = item.text.toString()
                    val addCheese = cbCheese.isChecked
                    val addOnion = cbOnion.isChecked
                    val addSalad = cbSalad.isChecked
                    val order1 = "He has ordered $option with:"+
                            (if(addCheese) "\nCheese" else "") + (if(addOnion) "\nOnion" else "") +
                            (if(addSalad) "\nSalad" else "")
                   Intent(this@SecondActivity,ThirdActivity::class.java).also{
                       it.putExtra(ThirdActivity.ORDER,order1)
                       startActivity(it)
                   }
                }
    }
}