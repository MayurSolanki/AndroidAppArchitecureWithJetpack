package com.beepnbuy.seller.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Mayur Solanki on 28/06/21, 5:31 pm.
 */
data class DashboardResponseModel(
    @SerializedName("products") val homeProductItemDataModelList: List<HomeProductItemDataModel>
    )
