package com.beepnbuy.seller.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Mayur Solanki on 28/06/21, 5:31 pm.
 */
data class DashboardRequestModel(
    @SerializedName("userId") val userid: String?,
    @SerializedName("latitude") val latitude : String?,
    @SerializedName("longitude") val longitude : String?,
    @SerializedName("cartToken") val cartToken : String?
    )
