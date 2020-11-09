package com.example.restaurant
//import android.support.v4.
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.restaurant.Model.DataManager
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import com.example.restaurant.Model.RestaurantInfo


@RunWith(AndroidJUnit4::class)
class CreateNewDishTest{
    @Rule @JvmField
    val dishListActivity = ActivityScenarioRule (ListRestaurantDishesActivity::class.java)

    @Test
    fun createNewDish(){
        val restaurant  = DataManager.restaurants["1"]!!
        val dishTitle = "This is the new title of dish"
        val dishText = "This is the text"

        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.spinnerRestaurant)).perform(click())
        onData(allOf(instanceOf(RestaurantInfo::class.java), equalTo(restaurant))).perform(click())



        onView(withId(R.id.textDishTitle)).perform(typeText(dishTitle))
        onView(withId(R.id.textDishText)).perform(typeText(dishText))

        Espresso.pressBack()
        Espresso.pressBack()

        val newDish = DataManager.dishes.last()
        assertEquals(restaurant,newDish.dish)
        assertEquals(dishTitle,newDish.Title)
        assertEquals(dishText,newDish.text)

    }
}