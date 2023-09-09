package com.codingblocks.quizapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.codingblocks.quizapp.R
import com.codingblocks.quizapp.models.Question
import com.codingblocks.quizapp.models.Quiz

class OptionAdapter(private val context:Context,private val question: Question):
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options: List<String> = listOf(question.option1,question.option2,question.option3,question.option4)

    inner class OptionViewHolder(itemView: View): ViewHolder(itemView){
        var optionView: TextView = itemView.findViewById(R.id.tvOption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener{
//            Toast.makeText(context,options[position]+" Selected",Toast.LENGTH_SHORT).show()
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == options[position])
        {
           holder.itemView.setBackgroundResource(R.drawable.option_selected_item_bg)
        }
        else
        {
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }

    }
    override fun getItemCount(): Int {
        return options.size
    }
}