package com.example.restaurant.Model

class RestaurantInfo(var IdRestaurant:String, var Title:String ){
    override fun toString(): String{
        return Title
    }
}


class DishInfo(var dish:RestaurantInfo, var Title:String, var Note:String){

}