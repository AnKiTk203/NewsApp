package com.codingblocks.quizapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.codingblocks.quizapp.R
import com.codingblocks.quizapp.adapters.Adapter
import com.codingblocks.quizapp.databinding.ActivityMainBinding
import com.codingblocks.quizapp.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.NullValue
import java.lang.Exception
import java.sql.Date
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var adapter: Adapter
    lateinit var fireStore: FirebaseFirestore
    private var quizList = mutableListOf<Quiz>()
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        setUpViews()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        this.binding.btnDatePicker.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                //                Toast.makeText(this,"Date "+datePicker.headerText+" added",Toast.LENGTH_SHORT).show()
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener{
                Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
            }
            datePicker.addOnCancelListener{
                Toast.makeText(this,"Back Button Clicked",Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun setUpViews() {
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerView()
    }

    private fun setUpFireStore() {
        fireStore = FirebaseFirestore.getInstance()
        val collectionReference = fireStore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error!=null)
            {
                Toast.makeText(this,"Error Fetching Data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        adapter = Adapter(this, quizList)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
        binding.navigationDrawer.setNavigationItemSelectedListener {
         val intent =  when(it.itemId) {
             R.id.btnProfile ->
                 Intent(this, ProfileActivity::class.java)

             else ->
                 Intent(this, MainActivity::class.java)
         }
            startActivity(intent)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}