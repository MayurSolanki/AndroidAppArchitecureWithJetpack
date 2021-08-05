package com.beepnbuy.seller.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.beepnbuy.seller.MainNavGraphDirections
import com.beepnbuy.seller.R
import com.beepnbuy.seller.data.DataState
import com.beepnbuy.seller.viewmodel.HomeViewModel
import com.beepnbuy.seller.databinding.ActivityHomeBinding
import com.beepnbuy.seller.databinding.ActivitySplashBinding
import com.beepnbuy.seller.interfaces.LocationPostSocket
import com.beepnbuy.seller.viewmodel.SplashViewModel
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*import javax.inject.Inject

/**
 * Created by Mayur Solanki on 25/06/21, 2:36 pm.
 */

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var activitySplashBinding: ActivitySplashBinding
    private  val splashViewModel: SplashViewModel by viewModels<SplashViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)
        lifecycle.addObserver(splashViewModel)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)

            startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
            finish()
        }


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