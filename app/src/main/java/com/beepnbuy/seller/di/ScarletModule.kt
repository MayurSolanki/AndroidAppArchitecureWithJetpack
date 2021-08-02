package com.beepnbuy.seller.di

import com.beepnbuy.seller.interfaces.LocationPostSocket
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Mayur Solanki on 30/07/21, 2:16 pm.
 */
@Module
@InstallIn(SingletonComponent::class)
object  ScarletModule {

    @Provides
    @Singleton
    fun provideScarlet(okHttpClient: OkHttpClient): Scarlet = Scarlet.Builder()
        .webSocketFactory(okHttpClient.newWebSocketFactory("wss://echo.websocket.org:443"))
        .backoffStrategy(LinearBackoffStrategy(5000))
        .addMessageAdapterFactory(GsonMessageAdapter.Factory())
//        .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
        .addStreamAdapterFactory(RxJava2StreamAdapterFactory())

        .build()



    @Provides
    @Singleton
    fun provideScarletInstance(scarlet: Scarlet): LocationPostSocket {
        return scarlet.create<LocationPostSocket>()
    }


}