package com.example.restaurant.Model

class DataManager {
    val restaurants = HashMap<String,RestaurantInfo>()
    val dishes =ArrayList<DishInfo>()

    init{
        initializeRestaurants()
    }


    private fun initializeRestaurants(){
        var restaurant = RestaurantInfo("1","Milaneso shack")
        restaurants.set(restaurant.IdRestaurant,restaurant);

        restaurant = RestaurantInfo("2","Kotlin palace")
        restaurants.set(restaurant.IdRestaurant,restaurant);

        restaurant = RestaurantInfo("3","Fast unix")
        restaurants.set(restaurant.IdRestaurant,restaurant);

        restaurant = RestaurantInfo("4","Macdonalds")
        restaurants.set(restaurant.IdRestaurant,restaurant);
    }

}