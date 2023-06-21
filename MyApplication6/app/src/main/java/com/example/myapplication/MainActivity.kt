package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = mutableListOf(
            todo("Follow AndroidDevs",false),
            todo("Learn about Recycler View",false),
            todo("Feed My Cat",false)
        )
        val adapter =TodoAdapter(todoList)
        val rvTodos = findViewById<RecyclerView>(R.id.rvTodos)
        val btnTodo = findViewById<Button>(R.id.btnTodo)
        val etTodo=findViewById<EditText>(R.id.etTodo)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)
       btnTodo.setOnClickListener{
           val title = etTodo.text.toString()
           val todo = todo(title,false)
           todoList.add(todo)
           adapter.notifyItemInserted(todoList.size-1)
       }

    }
}


