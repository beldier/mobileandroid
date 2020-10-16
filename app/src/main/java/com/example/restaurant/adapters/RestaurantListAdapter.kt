package com.example.restaurant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.Model.TagModel
import com.example.restaurant.R

class RestaurantListAdapter(private val context: Context, private val tags:MutableList<TagModel>):
        RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textRestaurant = itemView?.findViewById<TextView?>(R.id.textViewRestaurant)
        val textTag = itemView?.findViewById<TextView?>(R.id.textViewNameTag)
        val btnGetImageRestaurant = itemView?.findViewById<Button>(R.id.buttonImageTag)
        val imageViewRestaurant = itemView?.findViewById<ImageView>(R.id.imageViewTag)

        var tagPosition = 0

        init{
            btnGetImageRestaurant?.setOnClickListener{

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val itemView = layoutInflater.inflate(R.layout.item_tag_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tag = tags[position]
        holder.textRestaurant?.text =tag.restaurantName
        holder.textTag?.text = tag.tagName
        holder.tagPosition = position
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}