package com.example.restaurant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MySecondReceiver:BroadcastReceiver() {
    val TAG = MyFirstReceiver::class.simpleName
    override fun onReceive(context: Context?, p1: Intent?) {
        Log.i(TAG,"Hello 2nd receiver thread name: "+ Thread.currentThread().name)
        Toast.makeText(context, " Hello from 2nd receiver", Toast.LENGTH_SHORT).show()
    }
}