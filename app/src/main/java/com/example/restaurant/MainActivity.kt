package com.example.restaurant

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.restaurant.Model.DataManager
import com.example.restaurant.Model.RestaurantInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var dishPosition = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapterRestaurant = ArrayAdapter<RestaurantInfo>(
            this, android.R.layout.simple_spinner_item,
            DataManager.restaurants.values.toList()
        )
        adapterRestaurant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterRestaurant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRestaurant.adapter = adapterRestaurant;

        dishPosition = intent.getIntExtra(EXTRA_DISH_POSITION, POSITION_NOT_SET)

        if(dishPosition != POSITION_NOT_SET){
            displayDish()
        }
    }

    private fun displayDish() {
        val dish = DataManager.dishes[dishPosition]
        editTextTitle.setText(dish.Title)
        editTextText.setText(dish.Note)

        val restaurantPosition = DataManager.restaurants.values.indexOf(dish.dish)

        spinnerRestaurant.setSelection(restaurantPosition)
    }
}