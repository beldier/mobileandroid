package com.example.restaurant

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.restaurant.databinding.ActivityMyServices2Binding
import com.example.restaurant.databinding.ActivityMyServicesBinding
import com.example.restaurant.services.MyBoundService
import com.example.restaurant.services.MyBoundService.MyLocalBinder
import com.example.restaurant.services.MyMessengerService
import kotlinx.android.synthetic.main.activity_my_services2.*
import java.lang.Exception

class MyServices2Activity : AppCompatActivity() {
    var isBound = false
    private var myBoundService: MyBoundService?=null
    lateinit var mService:Messenger
    lateinit var  textViewRes : TextView

    inner class IncomingResponseHandler: Handler(){
        override fun handleMessage(messageFromService: Message) {
            super.handleMessage(messageFromService)
            if(messageFromService != null && messageFromService.what ==88){
                var bundle = messageFromService.data
                var result = bundle.getInt("result",0)
                binding.textView4.setText(result.toString())
            }
            else{
                super.handleMessage(messageFromService)
            }
        }
    }
    private val incomingMessenger = Messenger(IncomingResponseHandler())
    lateinit var binding: ActivityMyServices2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_services2)
        binding = DataBindingUtil.setContentView<ActivityMyServices2Binding>(this,R.layout.activity_my_services2)
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

        binding.buttonBoundService.setOnClickListener(){
            val intent = Intent(this, MyMessengerService::class.java)
            bindService(intent, mConnection, BIND_AUTO_CREATE)
        }

        binding.buttonStop.setOnClickListener(){
            if(isBound){
                unbindService(mConnection)
                isBound = false
            }
        }

        binding.buttonAddMessenger.setOnClickListener(){
            val msgToService = Message.obtain(null,43)
            var bundle = Bundle()
            bundle.putInt("numOne",10)
            bundle.putInt("numTwo",20)
            msgToService.data = bundle
            msgToService.replyTo = incomingMessenger

            try{
                mService.send(msgToService)
            }
            catch (ex:Exception){
                ex.printStackTrace()
            }
        }
    }
// 48:54 ------------------------------------------------------------------------------------------------------------
    private val mConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            val myLocalBinder = iBinder as MyLocalBinder
//            myBoundService = myLocalBinder.GetMyBoundService()
            mService = Messenger(service)
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }


    private val _serviceConnection = object: ServiceConnection{
        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val myLocalBinder = p1 as MyLocalBinder
            myBoundService = myLocalBinder.GetMyBoundService()
            isBound = true
        }

    }
    override fun onStart() {
        super.onStart()
        val intent = Intent(this,MyBoundService::class.java)
        bindService(intent, _serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        if(isBound){
            unbindService((_serviceConnection))
            isBound = false
        }
    }
}