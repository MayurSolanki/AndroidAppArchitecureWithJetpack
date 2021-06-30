package com.emxcel.beepnbuy.data.remote

import com.google.gson.annotations.SerializedName

data class BeepResponse(
    @SerializedName("responseMessage") val message: String?,
    @SerializedName("responseCode") val code: Int
)