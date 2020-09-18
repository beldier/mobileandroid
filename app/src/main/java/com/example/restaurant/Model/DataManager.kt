package com.example.restaurant.Model

object  DataManager {
    val restaurants = HashMap<String,RestaurantInfo>()
    val dishes =ArrayList<DishInfo>()

    init{
        initializeRestaurants()
        initializeDishes()
    }

    private fun initializeDishes() {
        var restaurant = restaurants["1"]!!
        var dish = DishInfo(restaurant,"Milanesa","Llajua with hot meat")
        dishes.add((dish))

        dish = DishInfo(restaurant,"Silpancho","Yum!")
        dishes.add(dish)

        restaurant = restaurants["2"]!!
        dish = DishInfo(restaurant,"Pique","sausages with potatoes")
        dishes.add(dish)

        restaurant = restaurants["3"]!!
        dish = DishInfo(restaurant,"Lomito","bread with meat")
        dishes.add(dish)

        restaurant = restaurants["4"]!!
        dish = DishInfo(restaurant,"Sonso","yuca")
        dishes.add(dish)
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