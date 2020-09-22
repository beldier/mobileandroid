package com.example.restaurant

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.restaurant.Model.DataManager
import com.example.restaurant.Model.DishInfo
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

        dishPosition = savedInstanceState?.getInt(EXTRA_DISH_POSITION, POSITION_NOT_SET)?:
                intent.getIntExtra(EXTRA_DISH_POSITION, POSITION_NOT_SET)



        if(dishPosition != POSITION_NOT_SET){
            displayDish()
        }else{
            createDish()
        }
    }

    private fun createDish() {
        DataManager.dishes.add(DishInfo())
        dishPosition = DataManager.dishes.lastIndex
    }

    override fun onPause() {
        super.onPause()
        saveDish()
    }

    private fun saveDish() {
        val dish = DataManager.dishes[dishPosition]
        dish.Title =  editTextTitle.text.toString()
        dish.Note = editTextText.text.toString()
        dish.dish = spinnerRestaurant.selectedItem as RestaurantInfo
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_DISH_POSITION,dishPosition)
    }

    private fun displayDish() {
        val dish = DataManager.dishes[dishPosition]
        editTextTitle.setText(dish.Title)
        editTextText.setText(dish.Note)

        val restaurantPosition = DataManager.restaurants.values.indexOf(dish.dish)

        spinnerRestaurant.setSelection(restaurantPosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings-> true
            R.id.action_next->{
                MoveNext()
            }
            else -> super.onOptionsItemSelected(item)

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(dishPosition >= DataManager.dishes.lastIndex){
            var item =menu?.findItem(R.id.action_next)
            if(item!=null){
                item.icon = getDrawable(R.drawable.ic_block_block_24)
                item.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun invalidateOptionsMenu() {
        super.invalidateOptionsMenu()
    }

    private fun MoveNext() {
        dishPosition++
        displayDish()
        invalidateOptionsMenu()
    }

}