package com.example.cheapjetshark.models.stores

import com.example.cheapjetshark.utils.Constants

data class Stores(
    val storeID: String,
    val storeName: String
) {
    fun getBanner(): String {
        return "${Constants.BANNERS_URL}$storeID.png"
    }

    fun getLogo(): String{
        return "${Constants.LOGOS_URL}$storeID.png"
    }

    fun getIcon(): String{
        return "${Constants.ICONS_URL}$storeID.png"
    }
}