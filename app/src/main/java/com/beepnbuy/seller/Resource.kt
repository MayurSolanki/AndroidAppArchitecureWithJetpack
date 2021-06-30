package com.beepnbuy.seller

/**
 * Created by Mayur Solanki on 19/05/21, 4:30 pm.
 */
sealed class Resource<T>(
    val data : T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data : T) : Resource<T>(data)
    class Loading<T>(data : T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data : T? = null) : Resource<T>(data, throwable)
}