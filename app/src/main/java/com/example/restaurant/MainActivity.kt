package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.restaurant.Model.DataManager
import com.example.restaurant.Model.DishInfo
import com.example.restaurant.Model.RestaurantInfo
import com.example.restaurant.databinding.ActivityMainBinding
import com.example.restaurant.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.spinnerRestaurant
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val tag = this::class.java.simpleName
    private var dishPosition = POSITION_NOT_SET

    val helper = DishGetLocationHelper(this,lifecycle)

    private val viewModel:MainViewModel by lazy{
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        buttonSurvey.setOnClickListener {
            startActivity(Intent(this, SurveyActivity::class.java))
        }

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

        Log.d(tag,"OnCreate")
    }

    private fun createDish() {
        dishPosition = viewModel.createNewDish()
    }

    override fun onPause() {
        super.onPause()
        saveDish()
        Log.d(tag,"OnPause")
    }

    private fun saveDish() {
        viewModel.updateDish(dishPosition,spinnerRestaurant.selectedItem as RestaurantInfo)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_DISH_POSITION,dishPosition)
    }

    private fun displayDish() {
        val dish = DataManager.dishes[dishPosition]
        viewModel.displayDish(dishPosition)
//        textDishTitle.setText(dish.Title)
//        textDishText.setText(dish.text)

        Log.i(tag,"Displaying not for position $dishPosition")
        val restaurantPosition = DataManager.restaurants.values.indexOf(dish.restaurant)

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
                helper.sendMessage(DataManager.dishes[dishPosition])
                MoveNext()
                true

            }
            else -> super.onOptionsItemSelected(item)

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(dishPosition >= DataManager.dishes.lastIndex){
            Log.i(tag,"Cambio de icono $dishPosition max value of dishes ${DataManager.dishes.lastIndex}")
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