package com.beepnbuy.seller.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.beepnbuy.seller.shareWhileObserved
import dev.shreyaspatil.noty.utils.ConnectionState
import dev.shreyaspatil.noty.utils.observeConnectivityAsFlow
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mayur Solanki on 09/07/21, 12:14 pm.
 */
abstract class BaseActivity : AppCompatActivity()  {

    private lateinit var connectionState: Flow<ConnectionState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeConnectivity()
    }


    private fun observeConnectivity() {
        connectionState = this
            .observeConnectivityAsFlow()
            .shareWhileObserved(this.lifecycleScope)
            .also { flow ->
                flow.asLiveData().observe(this) { state ->
                    when (state) {
                        ConnectionState.Available -> onConnectivityAvailable()
                        ConnectionState.Unavailable -> onConnectivityUnavailable()
                    }
                }
            }
    }
    fun applicationContext(): Context = applicationContext

    abstract fun onConnectivityAvailable()

    abstract fun onConnectivityUnavailable()


}



