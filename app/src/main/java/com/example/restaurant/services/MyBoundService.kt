package com.example.restaurant.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyBoundService : Service() {

    private val myLocalBinder = MyLocalBinder()

    inner class MyLocalBinder: Binder(){

        fun GetMyBoundService():MyBoundService{
            return this@MyBoundService
        }
    }
    override fun onBind(p0: Intent?): IBinder? {
        return  MyLocalBinder()
    }

    fun add(a: Int, b: Int): Int{
        return a+b
    }
    fun subtract(a: Int, b: Int): Int{
        return a - b
    }
    fun multiply(a: Int, b: Int): Int{
        return a * b
    }
    fun divide(a: Int, b: Int): Float{
        return a.toFloat() / b.toFloat()
    }
}