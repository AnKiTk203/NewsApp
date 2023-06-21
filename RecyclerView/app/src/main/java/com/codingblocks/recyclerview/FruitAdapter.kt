package com.codingblocks.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class FruitAdapter(
     val fruits: ArrayList<Fruit>
 ):RecyclerView.Adapter<FruitAdapter.FruitViewHolder>(){

     @SuppressLint("SuspiciousIndentation")
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
     val itemView = LayoutInflater.from(parent.context).inflate(
          R.layout.list_item_fruit,parent,false
      )
         return FruitViewHolder(itemView)
     }

     override fun getItemCount(): Int {
      return fruits.size
     }

     override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
         holder.itemView.findViewById<TextView>(R.id.tvFruitName).text = fruits[position].name
         holder.itemView.findViewById<TextView>(R.id.tvOrigin).text = fruits[position].origin
         holder.itemView.findViewById<TextView>(R.id.tvQuantity).text = fruits[position].quantity.toString()
     }
  class  FruitViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
 }