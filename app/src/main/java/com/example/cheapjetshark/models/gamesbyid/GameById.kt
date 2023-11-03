package com.example.cheapjetshark.models.gamesbyid

data class GameById(
    val cheapestPriceEver: CheapestPriceEver?,
    val deals: List<Deal>?,
    val info: Info?
)