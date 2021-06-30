package com.beepnbuy.seller

import android.app.Application
import com.beepnbuy.seller.repository.AppPreference
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 25/06/21, 5:40 pm.
 */

@HiltAndroidApp
class MyApplication : Application() {



    override fun onCreate() {
        super.onCreate()
    }


}