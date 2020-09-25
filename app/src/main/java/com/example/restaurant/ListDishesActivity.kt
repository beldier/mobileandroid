package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.Model.DataManager
import com.example.restaurant.Model.DishInfo
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_list_dishes.*

class ListDishesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_dishes)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            var intentMain = Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }

        listViewDishes.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,DataManager.dishes)

        listViewDishes.setOnItemClickListener { parent, view, position, id ->
            val activityMainIntent = Intent(this,MainActivity::class.java)
            activityMainIntent.putExtra(EXTRA_DISH_POSITION,position)
            startActivity(activityMainIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        (listViewDishes.adapter as ArrayAdapter<DishInfo>).notifyDataSetChanged()
    }
}