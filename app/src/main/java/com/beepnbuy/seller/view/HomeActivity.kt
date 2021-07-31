package com.beepnbuy.seller.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.beepnbuy.seller.data.DataState
import com.beepnbuy.seller.viewmodel.HomeViewModel
import com.beepnbuy.seller.databinding.ActivityHomeBinding
import com.beepnbuy.seller.interfaces.LocationPostSocket
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*import javax.inject.Inject

/**
 * Created by Mayur Solanki on 25/06/21, 2:36 pm.
 */

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var activityHomeBinding: ActivityHomeBinding
    private  val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var  locationPostSocket :  LocationPostSocket




    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)


        locationPostSocket.observeWebSocketEvent()
            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
            .subscribe {
                locationPostSocket.sendSubscribe("I am listening as a client")
                  locationPostSocket.observeNews()
                    .subscribe {
                        Log.d("Socket: ", "Received from server :"+it)
                    }

            }





        lifecycle.addObserver(homeViewModel)

        runBlocking {

            CoroutineScope(Dispatchers.IO).launch {

                CoroutineScope(Dispatchers.IO).launch {
                    // prceoss 1, 10 sec
                    Log.d("", " child 1 start")
                    delay(10000)


                }

                CoroutineScope(Dispatchers.IO).launch {
                    // prceoss 2, 5 sec
                    Log.d("", " child 2 start")
                    delay(5000)

                }
            }

            Log.d("", " parent completed")
        }


        onConnectivityAvailable()

//        homeViewModel.testMthd1().observe(this,{
//              when(it)  {
//                  is DataState.Error -> Toast.makeText(this,"show error",Toast.LENGTH_SHORT).show()
//                  is DataState.Loading -> Toast.makeText(this,"show progress",Toast.LENGTH_SHORT).show()
//                  is DataState.Success -> Toast.makeText(this,"${it.data!!}",Toast.LENGTH_LONG).show()
//              }
//        })


        homeViewModel.dataState.observe(this,{
            when(it)  {
                is DataState.Error -> Toast.makeText(this,"show error ${it.exception} == ${it.errorCode}",Toast.LENGTH_SHORT).show()
                is DataState.Loading -> Toast.makeText(this,"show progress",Toast.LENGTH_SHORT).show()
                is DataState.Success -> Toast.makeText(this," size : ${it.data!!.size}",Toast.LENGTH_LONG).show()
            }
        })





    }

    override fun onStart() {
        super.onStart()
    }



     override fun onConnectivityAvailable() {
        Log.d("TAG","onConnectivityAvailable")
    }

    override fun onConnectivityUnavailable() {
        Log.d("TAG","onConnectivityUnavailable")
    }





}