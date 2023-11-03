package com.example.cheapjetshark.utils

import com.example.cheapjetshark.models.stores.Stores

object Constants {
    const val RELATIVE_URL = "https://www.cheapshark.com"
    const val BASE_URL = "https://www.cheapshark.com/api/1.0/"

    const val BANNERS_URL = "$RELATIVE_URL/img/stores/banners/"
    const val LOGOS_URL = "$RELATIVE_URL/img/stores/logos/"
    const val ICONS_URL = "$RELATIVE_URL/img/stores/icons/"


    const val FB_NETWORK_ERROR =
        "com.google.firebase.FirebaseNetworkException: A network error (such as timeout, interrupted connection or unreachable host) has occurred."
    const val FB_WRONG_CREDENTIALS =
        "com.google.firebase.FirebaseException: An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]"
    const val FB_BADLY_FORMATTED_EMAIL =
        "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted."

    val StoreList: List<Stores> = listOf(
        Stores(storeID = "1", storeName = "Steam"),
        Stores(storeID = "2", storeName = "GamersGate"),
        Stores(storeID = "3", storeName = "GreenManGaming"),
        Stores(storeID = "4", storeName = "Amazon"),
        Stores(storeID = "5", storeName = "GameStop"),
        Stores(storeID = "6", storeName = "Direct2Drive"),
        Stores(storeID = "7", storeName = "GOG"),
        Stores(storeID = "8", storeName = "Origin"),
        Stores(storeID = "9", storeName = "Get Games"),
        Stores(storeID = "10", storeName = "Shiny Loot"),
        Stores(storeID = "11", storeName = "Humble Store"),
        Stores(storeID = "12", storeName = "Desura"),
        Stores(storeID = "13", storeName = "Uplay"),
        Stores(storeID = "14", storeName = "IndieGameStand"),
        Stores(storeID = "15", storeName = "Fanatical"),
        Stores(storeID = "16", storeName = "Gamesrocket"),
        Stores(storeID = "17", storeName = "Games Republi"),
        Stores(storeID = "18", storeName = "SilaGames"),
        Stores(storeID = "19", storeName = "Playfield"),
        Stores(storeID = "20", storeName = "ImperialGames"),
        Stores(storeID = "21", storeName = "WinGameStore"),
        Stores(storeID = "22", storeName = "FunStockDigital"),
        Stores(storeID = "23", storeName = "GameBillet"),
        Stores(storeID = "24", storeName = "Voidu"),
        Stores(storeID = "25", storeName = "Epic Games Stor"),
        Stores(storeID = "26", storeName = "Razer Game Store"),
        Stores(storeID = "27", storeName = "Gamesplanet"),
        Stores(storeID = "28", storeName = "Gamesload"),
        Stores(storeID = "29", storeName = "2Game"),
        Stores(storeID = "30", storeName = "IndieGala"),
        Stores(storeID = "31", storeName = "Blizzard Shop"),
        Stores(storeID = "32", storeName = "AllYouPlay"),
        Stores(storeID = "33", storeName = "DLGamer"),
        Stores(storeID = "34", storeName = "Noctre"),
        Stores(storeID = "35", storeName = "DreamGame")
    )
}
