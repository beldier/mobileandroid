package com.example.restaurant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.restaurant.Model.DataManager
import com.example.restaurant.Model.DishInfo
import com.example.restaurant.Model.RestaurantInfo

class MainViewModel : ViewModel() {

    private val _dishInfo: MutableLiveData<DishInfo> = MutableLiveData<DishInfo>()
    private val _likes = MutableLiveData<Int>()

    init{
        _likes.value = 0
    }
    val  likes:LiveData<Int> = _likes

    val popularity: LiveData<Popularity> =  Transformations.map(_likes){
        when{
            it >9 -> Popularity.STAR
            it >4-> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }
    fun onLike(){
        _likes.value=(_likes.value?:0)+1
    }

    val dishInfo: LiveData<DishInfo>
        get() = _dishInfo

    fun displayDish(dishPosition: Int){
        _dishInfo.value = DataManager.dishes[dishPosition]
    }
    fun createNewDish():Int{
        val dish = DishInfo()
        _dishInfo.value = dish
        DataManager.dishes.add(dish)
        return DataManager.dishes.lastIndex
    }
    fun updateDish(dishPosition: Int,restaurant:RestaurantInfo){
        val dish = DataManager.dishes[dishPosition]
        dish.Title = _dishInfo.value?.Title
        dish.text = _dishInfo.value?.text
        dish.restaurant = restaurant
    }
}

enum class Popularity{
    NORMAL,
    POPULAR,
    STAR
}