package com.example.restaurant

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.Model.DataManager
import com.example.restaurant.viewmodel.ListRestaurantDishesActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_restaurant_dishes.*
import kotlinx.android.synthetic.main.content_list_restaurant_dishes.*


class ListRestaurantDishesActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val dishLayoutManager by lazy{LinearLayoutManager(this)}

    private val dishRecyclerAdapter by lazy{ DishRecyclerAdapter(this, DataManager.dishes)}


    private val restaurantLayoutManager by lazy{
        GridLayoutManager(this,resources.getInteger(R.integer.restaurant_grid_span))
    }
    private val restaurantRecyclerAdapter by lazy{
        RestaurantRecyclerAdapter(this,DataManager.restaurants.values.toList())
    }
    private val viewModel by lazy{
        ViewModelProvider(this)[ListRestaurantDishesActivityViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_restaurant_dishes)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            var intentMain = Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        handleDisplaySelection(viewModel.navDrawerDisplaySelection)
        val toggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun displayDishes() {
        recyclerlistDishes.layoutManager = dishLayoutManager
        recyclerlistDishes.adapter = dishRecyclerAdapter
    }
    private fun displayRestaurants(){
        recyclerlistDishes.layoutManager = restaurantLayoutManager
        recyclerlistDishes.adapter = restaurantRecyclerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.list_restaurant_dishes, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        recyclerlistDishes.adapter?.notifyDataSetChanged()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_restaurant,
            R.id.nav_dishes->{
                handleDisplaySelection(item.itemId)
                viewModel.navDrawerDisplaySelection = item.itemId
            }

            R.id.nav_share->{
               val activityIntent = Intent(this,ListTagActivity::class.java)
                startActivity(activityIntent)
            }
            R.id.nav_send->{
                handleSelection(getString(R.string.send))
            }
            R.id.action_count_rish_restaurants->{
               val message = getString(R.string.how_many_restaurant_message,DataManager.dishes.size,DataManager.restaurants.size)
               Snackbar.make(recyclerlistDishes,message,Snackbar.LENGTH_LONG).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleDisplaySelection(itemId: Int) {
        when(itemId){
            R.id.nav_dishes ->{
                displayDishes()
            }
            R.id.nav_restaurant->{
                displayRestaurants()
            }
        }

    }

    private fun handleSelection(message: String) {
        Snackbar.make(recyclerlistDishes,message,Snackbar.LENGTH_LONG).show()
    }
}