package com.example.cheapjetshark.models.gamesbysearch

data class GamesBySearchItem(
    val cheapest: String,
    val cheapestDealID: String,
    val `external`: String,
    val gameID: String,
    val internalName: String,
    val steamAppID: String,
    val thumb: String
)