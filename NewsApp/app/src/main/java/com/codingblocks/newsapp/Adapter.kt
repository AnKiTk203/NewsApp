package com.codingblocks.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class Adapter(val onItemClicked: (Articles)->Unit,
              private val context: HomeFragment, articles: ArrayList<Articles>
):RecyclerView.Adapter<Adapter.HeadlinesViewHolder>()
{
    private val news: ArrayList<Articles> = articles
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
        currentItem.publishedAt.also { holder.date.text = it }
        Glide.with(context).load(currentItem.urlToImage).into(holder.image)
                holder.itemView.setOnClickListener {
                    onItemClicked(currentItem)
                }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<Articles>){
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
