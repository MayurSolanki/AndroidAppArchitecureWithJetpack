package com.beepnbuy.seller.repository

import com.beepnbuy.seller.AppConstant.ACCESS_TOKEN_REFRESH_API
import com.beepnbuy.seller.NetworkException
import com.beepnbuy.seller.fromJson
import com.beepnbuy.seller.getType
import com.beepnbuy.seller.toJsonString
import com.emxcel.beepnbuy.data.remote.BeepDataResponse
import com.emxcel.beepnbuy.data.remote.BeepResponse
import com.google.gson.Gson
import retrofit2.Response
import timber.log.Timber
import java.lang.reflect.Type
import java.net.SocketTimeoutException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Created by Mayur Solanki on 28/06/21, 3:15 pm.
 */
open class BaseRepository  {
 private val beepResponse = getType<BeepResponse>()
 private val beepDataResponse = getType<BeepDataResponse>()

@Suppress("BlockingMethodInNonBlockingContext")
protected suspend fun <T : Any> doNetworkCall(
    call: suspend () -> Response<BeepDataResponse>,
    resultType: Type,
    apiName: String,
    errorFun: suspend (errorMsg: String) -> Unit
): T? {
    @Suppress("UNCHECKED_CAST")
    return try {
        val response = call.invoke()
        when (response.code()) {
            in 200..299 -> { // success
                response.body()?.let {
                    when {
                        it.code == 401 -> {
                            Gson().fromJson<T>(it.toJsonString(), resultType)
                        }
                        it.code == 200 && resultType == beepResponse -> {
                            // return response without data
                            Gson().fromJson<T>(it.toJsonString(), resultType)
                        }
                        it.code == 200 && resultType == beepDataResponse -> {
                            Gson().fromJson<T>(it.toJsonString(), resultType)
                        }

                        it.code == 200 && it.data != null -> {
                            // return data field
                            Gson().fromJson<T>(it.data, resultType)
                        }
                        it.code == 201 && resultType == beepResponse -> {
                            // return data field
                            Gson().fromJson<T>(it.toJsonString(), resultType)
                        }

                        else -> {
                            // none of the case return error
                            it.message?.let { error -> errorFun(error) }
                            null
                        }
                    }
                }
            }
            in 400 until 500 -> { // client errors
                val error = response.errorBody()?.string()?.fromJson<BeepResponse>()
                Timber.e(
                    "Client Error(${response.code()}): ${response.message()} / ${response.errorBody()
                        ?.string()}"
                )
                errorFun(error?.message ?: "Something went wrong. (Client Error)")
                null
                //if(apiName == ACCESS_TOKEN_REFRESH_API) null else error as? T
            }
            in 500..600 -> { // server errors
                Timber.e("Server Error(${response.code()}): ${response.message()} / ${response.errorBody()}")
                errorFun("Something went wrong.(Internal Server Error)")
                null
            }
            else -> { // other errors
                Timber.e("Other Error(${response.code()}):${response.message()} / ${response.errorBody()}")
                errorFun("Something went wrong.")
                null
            }
        }
    } catch (ex: Exception) {
        Timber.e(ex, "Exceptions($apiName:${ex.javaClass}): ${ex.localizedMessage}")
        when (ex) {
            is CancellationException -> null // avoid error message for coroutine job cancellation
            is NetworkException -> {
                errorFun(ex.message ?: "No Internet")
                null
            }
            is SocketTimeoutException -> {
                errorFun("Time out.")
                null
            }
            else -> {
                errorFun("Something went Wrong")
                null
            }
        }
    } finally {
       // dismissProgress()
    }
}
}