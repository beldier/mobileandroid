package com.example.restaurant

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.Model.DishInfo

class DishRecyclerAdapter(private val context:Context, private val dishes:List<DishInfo>) : RecyclerView.Adapter<DishRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)


    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textRestaurant  = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textTitle  = itemView.findViewById<TextView>(R.id.textViewText)
        var dishPosition = 0
        //click event
        init{
            itemView.setOnClickListener{
                val intent = Intent(context,MainActivity::class.java)
                intent.putExtra(EXTRA_DISH_POSITION,dishPosition)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_dish_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes [position]
        holder.textRestaurant.text = dish.restaurant?.Title
        holder.textTitle.text = dish.Title
        holder.dishPosition = position
    }
}