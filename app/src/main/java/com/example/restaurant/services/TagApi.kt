package com.example.restaurant.services


import retrofit2.Call
import retrofit2.http.GET

interface TagApi {
    @GET(value="search?entity_id=84&entity_type=city")
    fun getRestaurantList():Call<String>

}