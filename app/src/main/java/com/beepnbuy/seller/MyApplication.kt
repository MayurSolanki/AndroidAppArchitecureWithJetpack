package com.beepnbuy.seller

import android.app.Application
import com.beepnbuy.seller.interfaces.LocationPostSocket
import dagger.hilt.android.HiltAndroidApp
import java.io.File
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 25/06/21, 5:40 pm.
 */

@HiltAndroidApp
public class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appCache = cacheDir

    }



    companion object {
        lateinit var appCache: File

        }
}