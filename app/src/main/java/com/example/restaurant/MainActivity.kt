package com.example.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.restaurant.Model.DataManager
import com.example.restaurant.Model.RestaurantInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dm = DataManager();
        val adapterRestaurant = ArrayAdapter<RestaurantInfo>(this,android.R.layout.simple_spinner_item,
            dm.restaurants.values.toList())
        adapterRestaurant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterRestaurant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRestaurant.adapter = adapterRestaurant;
    }
}