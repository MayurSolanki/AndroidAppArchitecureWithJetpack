package com.beepnbuy.seller.api

import com.emxcel.beepnbuy.data.remote.BeepDataResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Mayur Solanki on 25/06/21, 5:34 pm.
 */
@JvmSuppressWildcards
interface ApiClient{
    /** Generic [POST] Service
     * which response have status,message and data
     * - data can object,list,list of object,map
     *  - data can be null if there any error
     * */
    @FormUrlEncoded
    @POST("{endpoint}")
    suspend fun postApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String,
        @FieldMap(encoded = true) params: Map<String, String>
    ): Response<BeepDataResponse>


    @POST("{endpoint}")
    suspend fun postApiCallAsyncWithHeader(
        @Path("endpoint", encoded = true) endPoint: String,
        @QueryMap(encoded = true) params: Map<String, String>,
        @Header("Authorization")  Authorization:String
    ): Response<BeepDataResponse>

    @POST("{endpoint}")
    suspend fun postApiCallAsyncWithHeaderBody(
        @Path("endpoint", encoded = true) endPoint: String,
        @QueryMap(encoded = true) params: Map<String, String>,
        @Header("Authorization")  Authorization:String,
        @Body data: Any?
    ): Response<BeepDataResponse>

    /** Generic [POST] Service for Body request
     * which response have status,message and data
     * - data can object,list,list of object,map
     *  - data can be null if there any error
     * */
    @Headers("Content-Type:application/json")
    @POST("{endpoint}")
    suspend fun postBodyApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String,
        @Body data: Any
    ): Response<BeepDataResponse>

    /** Generic [GET] Service
     * which response have status,message and data
     * - data can object,list,list of object,map
     *  - data can be null if there any error
     * */
    @GET("{endpoint}")
    suspend fun getApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<BeepDataResponse>


    /** Generic [POST] Service for Body request
     * which response have status,message and data
     * - data can object,list,list of object,map
     *  - data can be null if there any error
     * */

    @DELETE("{endpoint}/{varientId}")
    suspend fun deleteApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String,
        @Path("varientId", encoded = true) varientId: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<BeepDataResponse>

    @DELETE("{endpoint}")
    suspend fun deleteApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String
    ): Response<BeepDataResponse>

    @DELETE("{endpoint}")
    suspend fun deleteApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<BeepDataResponse>


    @GET("{endpoint}")
    suspend fun getApiCallAsyncAuthHeader(
        @Path("endpoint", encoded = true) endPoint: String,
        @QueryMap(encoded = true) params: Map<String, String>,
        @Header("Authorization")  Authorization:String
    ): Response<BeepDataResponse>

    @Headers("Content-Type:application/json")
    @PUT("{endpoint}")
    suspend fun putBodyApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String,
        @Body data: Any
    ): Response<BeepDataResponse>

    /** Generic [PUT] Service
     * which response have status,message and data
     * - data can object,list,list of object,map
     *  - data can be null if there any error
     * */
    @PUT("{endpoint}")
    suspend fun putApiCallAsync(
        @Path("endpoint", encoded = true) endPoint: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<BeepDataResponse>

    @Headers("Content-Type:application/json")
    @HTTP(method = "DELETE",path = "{endpoint}",hasBody = true)
    suspend fun postBodyApiWithDeleteCallAsync(
        @Body data: Any,
        @Path("endpoint", encoded = true) endPoint: String

    ): Response<BeepDataResponse>

}
