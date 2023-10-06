package com.codingblocks.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Adapter(
    private val onItemClicked: (Headlines)->Unit
):RecyclerView.Adapter<Adapter.HeadlinesViewHolder>()
{
    private val news: ArrayList<Headlines> = ArrayList()
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
        holder.headline.text = currentItem.title
        holder.date.text = currentItem.publishedAt
        holder.image.setImageResource(currentItem.urlToImage.toInt())
                holder.itemView.setOnClickListener {
                    onItemClicked(currentItem)
                }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<Headlines>){
        news.clear()
        news.addAll(updatedNews)
        notifyDataSetChanged()
    }
    inner class HeadlinesViewHolder(itemView: View):ViewHolder(itemView)
    {
        var headline: TextView = itemView.findViewById(R.id.tvHeadLine)
        var date: TextView = itemView.findViewById(R.id.tvDate)
        var image: ImageView = itemView.findViewById(R.id.ivIcon)
    }
}
