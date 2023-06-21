package com.example.swipableviews
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
class ViewPagerAdapter (
    val images: List<Int>
        ):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val ivImage: ImageView = itemView.findViewById(R.id.ivImages)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
        return ViewPagerViewHolder(view)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
     val curImages= images[position]
        holder.ivImage.setImageResource(curImages)
    }
    override fun getItemCount(): Int {
        return images.size
    }
}
