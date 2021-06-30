package com.beepnbuy.seller.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeProductItemDataModel(
    @SerializedName("productItemId") val productItemId: String,
    @SerializedName("sku") val sku: String,
    @SerializedName("productItemName") val productItemName: String,
    @SerializedName("productItemImage") val productItemImage: String,
    @SerializedName("minPrice") val minPrice: Double?,
    @SerializedName("maxPrice") val maxPrice: Double?,
    @SerializedName("mrpPrice") val mrpPrice: Double?,
    @SerializedName("storePrice") val storePrice: Double?,
    @SerializedName("cartQuantity") var cartQuantity: Int,
    @SerializedName("isFavourite") var isFavourite: Int
): Parcelable