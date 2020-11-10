package com.example.restaurant.Model

class RestaurantInfo(var IdRestaurant: String, var Title: String) {
    override fun toString(): String {
        return Title
    }
}


data class DishInfo(
    var restaurant: RestaurantInfo? = null,
    var Title: String? = null,
    var text: String? = null
) {

}