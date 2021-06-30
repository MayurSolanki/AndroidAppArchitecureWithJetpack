package com.beepnbuy.seller.data

import java.lang.Exception

/**
 * Created by Mayur Solanki on 28/06/21, 2:49 pm.
 */


sealed class DataState <out T>(
    val data : T? = null,
){
     class Success<out T>(data: T): DataState<T>(data)
     class Error(val exception: String):DataState<Nothing>()
     object Loading: DataState<Nothing>()
}
