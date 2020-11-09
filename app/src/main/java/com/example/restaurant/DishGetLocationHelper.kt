package com.example.restaurant

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.restaurant.Model.DishInfo

class DishGetLocationHelper(val context: Context, val lifecycle:Lifecycle):LifecycleObserver {
    init{
        lifecycle.addObserver(this)
    }
    val tag = this::class.java.simpleName
    var currentLat = 0.0
    var currentLon = 0.0

    val locManager = PseudoLocationManager(context){ lat,lon->
        currentLat =lat
        currentLon =lon
        Log.d(tag,"Location callback Lat:$currentLat Log:$currentLon")
    }
    val msgManager = PseudoMessagingManager(context)
    var msgConnection : PseudoMessagingConnection?=null

    fun sendMessage(dish: DishInfo){
        val getTogetherMessage = "$currentLat|$currentLon|${dish.Title}|${dish.dish?.Title}"
        msgConnection?.send(getTogetherMessage)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumeHandler(){
        Log.d(tag,"resumeHandler")
        locManager.start()
        msgManager.connect{connection->
            Log.d(tag,"Connection callback - lifecycle state:${lifecycle.currentState}")
            if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
            {
                msgConnection = connection
            }
            else{
                connection.disconnect()
            }
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseHandler(){
        Log.d(tag,"pauseHandler")
        locManager.stop()
        msgConnection?.disconnect()
    }
}