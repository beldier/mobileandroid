package com.example.restaurant

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log

class PseudoLocationManager (private val content: Context, private val callback:(Double,Double)->Unit):Runnable
{
    private val tag = this::class.simpleName
    private val lattitudes = doubleArrayOf(40.9,176.9,59.74)
    private val longitudes = doubleArrayOf(40.9,176.9,59.74)

    private var locationIndex = 0

    private val callbackMiliseconds = 300L
    private var enable =false
    private val postHandler = Handler(Looper.getMainLooper())

    fun start(){
        enable = true
        Log.d(tag,"Location manager started")
        triggerCallbackAndScheduleNext()
    }
    fun stop(){
        enable = false
        postHandler.removeCallbacks(this)
        Log.d(tag,"Location manger stop")
    }
    private fun triggerCallbackAndScheduleNext(){
        callback(lattitudes[locationIndex],longitudes[locationIndex])
        locationIndex = (locationIndex+1)% lattitudes.size
        if(enable)
            postHandler.postDelayed(this,callbackMiliseconds)
    }

    override fun run() {
        triggerCallbackAndScheduleNext()
    }
}
