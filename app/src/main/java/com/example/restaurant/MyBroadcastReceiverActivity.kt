package com.example.restaurant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.restaurant.databinding.ActivityMyBroadcastReceiverBinding
import com.example.restaurant.receivers.MyFirstReceiver

class MyBroadcastReceiverActivity : AppCompatActivity() {

    lateinit var binding:ActivityMyBroadcastReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMyBroadcastReceiverBinding>(this,R.layout.activity_my_broadcast_receiver)

        binding.buttonSendNormal.setOnClickListener(){
            val intent = Intent(this, MyThirdReceiverInner::class.java)
//            val intent = Intent("my.custom.action.name")
            Intent().also {intent->
                intent.setAction("my.custom.action.name")
                sendBroadcast(intent)
            }
            Log.i("MyFirstReceiver","after send broadcast "+ Thread.currentThread().name)
        }
    }
    class MyThirdReceiverInner : BroadcastReceiver(){
        override fun onReceive(context: Context?, p1: Intent?) {
            Log.i("MyFirstReceiver","Hello from 3rd receiver"+ Thread.currentThread().name)
            Toast.makeText(context,"Hello from 3rd Receiver", Toast.LENGTH_LONG).show()
        }
    }
}