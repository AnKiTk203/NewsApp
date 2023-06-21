package com.codingblocks.myprojecttodoapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingblocks.myprojecttodoapp.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<ToDoModel>()
    var adapter = TodoAdapter(list)

    val db by lazy {
        ADatabase.getDatabase(this)
    }

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar2)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0
            )

        }
        binding.btnFB.setOnClickListener {
            Intent(this, TaskActivity::class.java).also {
                startActivity(it)
            }
        }

        setupRecyclerView()
        initSwipe()


    }


    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(list)

        binding.rvTodo.layoutManager = layoutManager
        binding.rvTodo.adapter = adapter

        // Observe the data from the Room database and update the adapter
        db.toDoDao().getTask().observe(this) { tasks ->
            list.clear()
            list.addAll(tasks)
            adapter.notifyDataSetChanged()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initSwipe() {
        val swipe = object : Swipe() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    GlobalScope.launch {
                        db.toDoDao().deleteTask(adapter.getItemId(position))
                    }
                    Toast.makeText(this@MainActivity, "Task Deleted", Toast.LENGTH_SHORT).show()
                } else if (direction == ItemTouchHelper.RIGHT) {
                    GlobalScope.launch {
                        db.toDoDao().deleteTask(adapter.getItemId(position))
                    }
                    Toast.makeText(this@MainActivity, "Task Finished", Toast.LENGTH_SHORT).show()
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(binding.rvTodo)
    }


}



