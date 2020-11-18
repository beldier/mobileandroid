package com.example.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.restaurant.databinding.ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySurveyBinding>(this, R.layout.activity_survey)

        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.NavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController,drawerLayout)
        NavigationUI.setupWithNavController(binding.navView,navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.NavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}