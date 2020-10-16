package com.example.restaurant.Model;
import android.net.Uri;

class TagModel(val restaurantName:String="None",val tagName:String="None",val urlPicture:String=""){
    fun getRestaurantImage():Uri{
        val urlImage: Uri =Uri.EMPTY
        return urlImage
    }
}
