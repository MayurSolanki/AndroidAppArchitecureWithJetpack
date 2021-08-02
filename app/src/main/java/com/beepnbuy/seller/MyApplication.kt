package com.beepnbuy.seller

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Mayur Solanki on 25/06/21, 5:40 pm.
 */

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}