package com.codingblocks.myprojecttodoapp

import android.os.Build.VERSION_CODES.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Random


class TodoAdapter(val list: List<ToDoModel>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(toDoModel: ToDoModel) {
            with(itemView) {
                val colors = resources.getIntArray(R.array.random_colors)
                val randomColor = colors[Random().nextInt(colors.size)]
                findViewById<View>(R.id.viewColorTag).setBackgroundColor(randomColor)
                findViewById<TextView>(R.id.tvShowTitle).text = toDoModel.title
                findViewById<TextView>(R.id.tvShowTask).text = toDoModel.description
                findViewById<TextView>(R.id.tvShowCategory).text = toDoModel.category
                updateTime(toDoModel.time)
                updateDate(toDoModel.date)
            }

        }

        private fun updateTime(time: Long) {
            val myFormat = "h:mm a"
            val sdf = SimpleDateFormat(myFormat)
            itemView.findViewById<TextView>(R.id.tvShowTime).text = sdf.format(time)
        }

        private fun updateDate(time: Long) {
            val myFormat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myFormat)
            itemView.findViewById<TextView>(R.id.tvShowDate).text = sdf.format(time)
        }
    }

}