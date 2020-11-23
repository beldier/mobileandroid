package com.example.restaurant.services

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast
import java.lang.Exception

class MyMessengerService: Service() {

    inner class IncomingHandler:Handler(){
        override fun handleMessage(messageFromActivity: Message) {
            super.handleMessage(messageFromActivity)
            if(messageFromActivity!= null  && messageFromActivity.what == 43){
                var bundle = messageFromActivity.data
                val numOne = bundle.getInt("numOne")
                val numTwo = bundle.getInt("numTwo")

                val res = addNumber(numOne,numTwo)

                Toast.makeText(applicationContext, " Result: "+res,Toast.LENGTH_SHORT).show()

                val incommingMessenger = messageFromActivity.replyTo
                val messageToActivity = Message.obtain(null, 88)
                var bundleMessage = Bundle()
                bundleMessage.putInt("result",res)

                messageToActivity.data = bundleMessage
                try{
                    incommingMessenger.send(messageToActivity)
                }
                catch (ex: Exception){
                    ex.printStackTrace()
                }
            }else {
                super.handleMessage(messageFromActivity)
            }
        }
    }
    val mMessenger = Messenger(IncomingHandler())

    override fun onBind(p0: Intent?): IBinder? {
        return mMessenger.binder
    }

    fun addNumber(a: Int, b: Int):Int{
        return a+b;
    }
}