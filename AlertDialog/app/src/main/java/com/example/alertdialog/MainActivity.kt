package com.example.alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setMessage("Add Contact")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("Yes") { _, _->
                Toast.makeText(this,"You added Mr Ankit to your contact list", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_->
                Toast.makeText(this,"Mission aborted",Toast.LENGTH_SHORT).show()
            }.create()
        val btn1=findViewById<Button>(R.id.btn1)
        val btn2=findViewById<Button>(R.id.btn2)
        val btn3=findViewById<Button>(R.id.btn3)
        btn1.setOnClickListener{
            addContactDialog.show()
        }
        val options = arrayOf("First Item","Second Item","Third Item")
        val singleChoiceDialog=AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setSingleChoiceItems(options,0){_, i->
                Toast.makeText(this,"You Clicked on ${options[i]}",Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept") { _, _->
                Toast.makeText(this,"Accepted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline"){_,_->
                Toast.makeText(this,"Declined",Toast.LENGTH_SHORT).show()
            }.create()
        btn2.setOnClickListener{
            singleChoiceDialog.show()
        }


        val multiChoiceDialog=AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setMultiChoiceItems(options, booleanArrayOf(false,false,false)){_, i,isChecked->
                if(isChecked) {
                    Toast.makeText(this, "You checked ${options[i]}",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "You unchecked ${options[i]}",Toast.LENGTH_SHORT).show()
                }

            }
            .setPositiveButton("Accept") { _, _->
                Toast.makeText(this,"Accepted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline"){_,_->
                Toast.makeText(this,"Declined",Toast.LENGTH_SHORT).show()
            }.create()
        btn3.setOnClickListener{
            multiChoiceDialog.show()
        }
    }
}