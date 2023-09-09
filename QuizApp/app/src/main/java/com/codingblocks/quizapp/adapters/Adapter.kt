package com.codingblocks.quizapp.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.codingblocks.quizapp.R
import com.codingblocks.quizapp.activities.MainActivity
import com.codingblocks.quizapp.activities.QuestionActivity
import com.codingblocks.quizapp.models.Quiz
import com.codingblocks.quizapp.util.ColorPicker
import com.codingblocks.quizapp.util.IconPicker

class Adapter(private val context: Context, private val quizzes: List<Quiz>):
    RecyclerView.Adapter<Adapter.QuizViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColors()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
            val it = Intent(context,QuestionActivity::class.java)
            it.putExtra("DATE",quizzes[position].title)
            context.startActivity(it)
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
        }
    }
    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}