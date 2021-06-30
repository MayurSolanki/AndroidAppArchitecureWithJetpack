package com.beepnbuy.seller.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.beepnbuy.seller.data.DataState
import com.beepnbuy.seller.viewmodel.HomeViewModel
import com.beepnbuy.seller.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mayur Solanki on 25/06/21, 2:36 pm.
 */

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var activityHomeBinding: ActivityHomeBinding
    private  val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        lifecycle.addObserver(homeViewModel)


        homeViewModel.dataState.observe(this,{
              when(it)  {
                  is DataState.Error -> Toast.makeText(this,"show error",Toast.LENGTH_SHORT).show()
                  is DataState.Loading -> Toast.makeText(this,"show progress",Toast.LENGTH_SHORT).show()
                  is DataState.Success -> Toast.makeText(this,"${it.data!!.homeProductItemDataModelList.size}",Toast.LENGTH_LONG).show()
              }

        })

    }

    override fun onStart() {
        super.onStart()


    }


}