package com.codingblocks.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Adapter(
    private val news: ArrayList<Headlines>,
    private val onItemClicked: (Headlines)->Unit
):RecyclerView.Adapter<Adapter.HeadlinesViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false)
        return HeadlinesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }
    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int)
    {
        val currentItem = news[position]
        holder.headline.text = currentItem.headline
        holder.date.text = currentItem.date
        holder.image.setImageResource(currentItem.image)
                holder.itemView.setOnClickListener {
                    onItemClicked(currentItem)
                }
    }
    inner class HeadlinesViewHolder(itemView: View):ViewHolder(itemView)
    {
        var headline: TextView = itemView.findViewById(R.id.tvHeadLine)
        var date: TextView = itemView.findViewById(R.id.tvDate)
        var image: ImageView = itemView.findViewById(R.id.ivIcon)
    }
}
