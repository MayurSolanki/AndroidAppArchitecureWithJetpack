package com.beepnbuy.seller.interfaces

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mayur Solanki on 30/07/21, 2:40 pm.
 */
interface LocationPostSocket {
    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>

    @Send
    fun sendSubscribe(sampleMessage: String)

    @Receive
    fun observeNews(): Flowable<String>
}