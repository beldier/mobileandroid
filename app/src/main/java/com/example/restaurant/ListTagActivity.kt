package com.example.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.Model.TagModel
import com.example.restaurant.adapters.RestaurantListAdapter
import com.example.restaurant.services.ServiceBuilder
import com.example.restaurant.services.TagApi
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_list_tag.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListTagActivity : AppCompatActivity() {
    var tagList:MutableList<TagModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tag)
        getListRestaurant()
    }

    fun getListRestaurant(){
        var tagService = ServiceBuilder.buildServiceScalar(TagApi::class.java)
        var call = tagService.getRestaurantList()
        call.enqueue(object: Callback<String>{

            override fun onResponse(call: Call<String>, response: Response<String>) {
                var jsonObject = JSONObject(response.body())
                var restaurants = jsonObject.get("restaurants") as JSONArray
                val restaurant = restaurants.getJSONObject(0)
                val e =restaurant.get("restaurant") as JSONObject
                val restaurantName = e.get("name")
                val restaurantTag = e.get("highlights") as JSONArray
                for (i in 0 until restaurantTag.length()) {
                    tagList.add(TagModel(restaurantName.toString() , restaurantTag[i].toString()))
                }
                var adapterTag  = RestaurantListAdapter(applicationContext,tagList)
                RecyclerViewTag.layoutManager = LinearLayoutManager(applicationContext)
                RecyclerViewTag.adapter = adapterTag

            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(applicationContext,"Failed :'v ",Toast.LENGTH_SHORT).show()
            }

        })

    }
}