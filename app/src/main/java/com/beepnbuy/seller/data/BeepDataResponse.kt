package com.emxcel.beepnbuy.data.remote

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class BeepDataResponse(
    @SerializedName("responseMessage") val message: String?,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("data") val data: JsonElement?,
)