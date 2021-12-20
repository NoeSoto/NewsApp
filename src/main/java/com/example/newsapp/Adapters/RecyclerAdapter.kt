package com.example.newsapp.Adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import retrofit2.http.Url
import java.util.logging.Level.parse

class RecyclerAdapter(val titles: List<String>,
                      val details: List<String>,
                      val images: List<String>,
                      val links: List<String>)
    :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        val itemDetail: TextView = itemView.findViewById(R.id.tv_description)
        val itemImage: ImageView = itemView.findViewById(R.id.iv_image)

        init{
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(links[position])

                startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]

        Glide.with(holder.itemImage)
            .load(images[position])
            .into(holder.itemImage)

    }

    override fun getItemCount(): Int {
        return titles.size
    }
}