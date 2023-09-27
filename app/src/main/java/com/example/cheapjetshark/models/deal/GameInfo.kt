package com.example.cheapjetshark.models.deal

data class GameInfo(
    val gameID: String,
    val metacriticLink: String,
    val metacriticScore: String,
    val name: String,
    val publisher: String,
    val releaseDate: Int,
    val retailPrice: String,
    val salePrice: String,
    val steamAppID: String,
    val steamRatingCount: String,
    val steamRatingPercent: String,
    val steamRatingText: String,
    val steamworks: String,
    val storeID: String,
    val thumb: String
)