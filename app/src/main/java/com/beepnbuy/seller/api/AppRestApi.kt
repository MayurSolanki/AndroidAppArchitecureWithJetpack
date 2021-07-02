package com.beepnbuy.seller.api

import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 28/06/21, 3:31 pm.
 */
interface AppRestApi {

    suspend fun <T : Any> postRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> postRequestWithHeader(
        endPoint: String,
        header: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> postRequestWithHeaderBody(
        endPoint: String,
        header: String,
        queryParams: Map<String, String>,
        body: Any?,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> postBodyRequest(
        endPoint: String,
        body: Any,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> getRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
         errorMessage: suspend (errorMsg: String) -> Unit
    ): T?

    suspend fun <T : Any> getRequestAuth(
        header: String,
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> deleteRequest(
        endPoint: String,
        varientId: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> deleteRequest(
        endPoint: String,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> deleteRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> putBodyRequest(
        endPoint: String,
        body: Any,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?

    suspend fun <T : Any> putRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?


    suspend fun <T : Any> postBodyRequestDelete(
        endPoint: String,
        body: Any,
        result: Type,
        errorMessage: suspend(errorMsg:String) -> Unit
    ): T?
}