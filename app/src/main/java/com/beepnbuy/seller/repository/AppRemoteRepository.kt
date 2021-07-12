package com.beepnbuy.seller.repository

import com.beepnbuy.seller.api.ApiClient
import com.beepnbuy.seller.api.AppRestApi
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 28/06/21, 3:14 pm.
 */

class AppRemoteRepository @Inject constructor(val apiClient: ApiClient):  BaseRepository(), AppRestApi {

    override suspend fun <T : Any> postRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.postApiCallAsync(endPoint = endPoint, params = queryParams) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }

    override suspend fun <T : Any> postRequestWithHeader(
        endPoint: String,
        header: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.postApiCallAsyncWithHeader(endPoint = endPoint, params = queryParams,header) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }

    override suspend fun <T : Any> postRequestWithHeaderBody(
        endPoint: String,
        header: String,
        queryParams: Map<String, String>,
        body:Any?,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.postApiCallAsyncWithHeaderBody(endPoint = endPoint, params = queryParams,header, body) },
            apiName = endPoint,
            resultType = result,
            errorFun =  errorMessage
        )
    }

    override suspend fun <T : Any> postBodyRequest(
        endPoint: String,
        body:Any,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.postBodyApiCallAsync(endPoint = endPoint, data = body) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }

    override suspend fun <T : Any> getRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.getApiCallAsync(endPoint = endPoint, params = queryParams) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }




    override suspend fun <T : Any> getRequestAuth(
        header: String,
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.getApiCallAsyncAuthHeader(endPoint = endPoint, params = queryParams, Authorization = header) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }


    override suspend fun <T : Any> deleteRequest(
        endPoint: String,
        varientId: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.deleteApiCallAsync(endPoint = endPoint,varientId = varientId, params = queryParams) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }

    override suspend fun <T : Any> deleteRequest(
        endPoint: String,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {

        return doNetworkCall(
            call = { apiClient.deleteApiCallAsync(endPoint = endPoint) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }
    override suspend fun <T : Any> deleteRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.deleteApiCallAsync(endPoint = endPoint, params = queryParams) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }
    override suspend fun <T : Any> putBodyRequest(
        endPoint: String,
        body:Any,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.putBodyApiCallAsync(endPoint = endPoint, data = body) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }

    override suspend fun <T : Any> putRequest(
        endPoint: String,
        queryParams: Map<String, String>,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.putApiCallAsync(endPoint = endPoint, params = queryParams) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }


    override suspend fun <T : Any> postBodyRequestDelete(
        endPoint: String,
        body:Any,
        result: Type,
        errorMessage: suspend (errorMsg: String, errorCode: Int) -> Unit
    ): T? {
        return doNetworkCall(
            call = { apiClient.postBodyApiWithDeleteCallAsync(endPoint = endPoint, data = body) },
            apiName = endPoint,
            resultType = result,
            errorFun = errorMessage
        )
    }


}