package com.example.restaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.Model.RestaurantInfo
import com.google.android.material.snackbar.Snackbar

class RestaurantRecyclerAdapter(private val context: Context, private val restaurants:List<RestaurantInfo>):
    RecyclerView.Adapter<RestaurantRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_restaurant_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.textRestaurant?.text = restaurant.Title
        holder.restaurantPosition = position
    }




    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!! ){
        val textRestaurant = itemView?.findViewById<TextView>(R.id.textRestaurant)
        var restaurantPosition = 0
        init {
            itemView?.setOnClickListener{
                Snackbar.make(it,restaurants[restaurantPosition]?.Title,Snackbar.LENGTH_LONG).show()
            }
        }
    }

}