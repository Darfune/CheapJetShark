package com.example.cheapjetshark.models.deal

data class Deal(
    val cheaperStores: List<CheaperStore>,
    val cheapestPrice: CheapestPrice,
    val gameInfo: GameInfo
)