package com.example.restaurant

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import com.example.restaurant.services.MyBoundService
import com.example.restaurant.services.MyBoundService.MyLocalBinder
import kotlinx.android.synthetic.main.activity_my_services2.*

class MyServices2Activity : AppCompatActivity() {
    var isBound = false
    private var myBoundService: MyBoundService?=null
    lateinit var  textViewRes : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_services2)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonDivider = findViewById<Button>(R.id.buttonDivider)
        val textViewRes = findViewById<TextView>(R.id.textViewRes)
        buttonAdd.setOnClickListener(){
            val res = myBoundService?.add(2,3)
            textViewRes.setText(res.toString())
        }
        buttonDivider.setOnClickListener(){
            val res = myBoundService?.divide(8,3)
            textViewRes.setText(res.toString())
        }
        myBoundService?.add(2,3)
        myBoundService?.divide(8,4)

    }

    private val mConnection: ServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
            val myLocalBinder = iBinder as MyLocalBinder
            myBoundService = myLocalBinder.GetMyBoundService()
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this,MyBoundService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        unbindService((mConnection))
        isBound = false
    }
}