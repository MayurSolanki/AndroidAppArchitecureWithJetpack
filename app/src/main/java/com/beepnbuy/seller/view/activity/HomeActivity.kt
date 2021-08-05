package com.beepnbuy.seller.view.activity

import android.annotation.SuppressLint
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
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var  locationPostSocket :  LocationPostSocket


    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        lifecycle.addObserver(homeViewModel)

        setSupportActionBar(activityHomeBinding.toolbar)

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

      //  setupActionBarWithNavController(navController) // for action bar
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.settingsFragment,R.id.notificationsFragment))
        activityHomeBinding.toolbar.setupWithNavController(navController,appBarConfiguration) //for toolbar


        locationPostSocket.observeWebSocketEvent()
            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
            .subscribe {
                locationPostSocket.sendSubscribe("I am listening as a client")
                  locationPostSocket.observeNews()
                    .subscribe {
                        Log.d("Socket: ", "Received from server :"+it)
                    }

            }


        activityHomeBinding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.homeFragment -> showBottomNav()
                R.id.settingsFragment -> showBottomNav()
                R.id.notificationsFragment -> showBottomNav()

                else -> hideBottomNav()

            }
        }



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

//    override fun onSupportNavigateUp(): Boolean { //for actionbar
//        return  navController.navigateUp() || super.onSupportNavigateUp()
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.item_about_app -> {
                val action = MainNavGraphDirections.actionGlobalAboutAppFragment()

                navController.navigate(action)
                return true
            }
            else -> return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showBottomNav() {
        activityHomeBinding.toolbar.visibility = View.VISIBLE
        activityHomeBinding.bottomNavView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        activityHomeBinding.bottomNavView.visibility = View.GONE

    }


}