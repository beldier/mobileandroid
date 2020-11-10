package com.example.restaurant.Model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DataManagerTest{
    @Before
    fun setUp(){
        DataManager.dishes.clear()
        DataManager.initializeDishes()
    }
    @Test
    fun addDish(){
        val restaurant  = DataManager.restaurants["1"]!!
        val dishTitle = "This is the new title of dish"
        val dishText = "This is the text"

        val index = DataManager.addDish(restaurant,dishTitle,dishText)
        val dish = DataManager.dishes[index]

        assertEquals(restaurant,dish.restaurant)
        assertEquals(dishTitle,dish.Title)
        assertEquals(dishText,dishText)
    }

    @Test
    fun findSimilarDishes(){
        val restaurant = DataManager.restaurants["1"]!!
        val dishTitle = " This is a test dish"
        val dishText1 = "This is th e body of my test dish"
        val dishText2 = "This is the body of my second text dish"

        val index1= DataManager.addDish(restaurant,dishTitle,dishText1)
        val index2 = DataManager.addDish(restaurant, dishTitle,dishText2)

        val dish1 = DataManager.findDish(restaurant, dishTitle, dishText1)
        val foundIndex1 = DataManager.dishes.indexOf(dish1)
        assertEquals(index1,foundIndex1)

        val dish2 = DataManager.findDish(restaurant,dishTitle,dishText2)
        val foundIndex2 = DataManager.dishes.indexOf(dish2)
        assertEquals(index2,foundIndex2)

    }
}