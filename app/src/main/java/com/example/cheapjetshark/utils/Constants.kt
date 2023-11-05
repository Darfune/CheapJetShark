package com.example.cheapjetshark.utils

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

    val StoreList: HashMap<String, String> = hashMapOf(
        "1" to "Steam",
        "2" to "GamersGate",
        "3" to "GreenManGaming",
        "4" to "Amazon",
        "5" to "GameStop",
        "6" to "Direct2Drive",
        "7" to "GOG",
        "8" to "Origin",
        "9" to "Get Games",
        "10" to "Shiny Loot",
        "11" to "Humble Store",
        "12" to "Desura",
        "13" to "Uplay",
        "14" to "IndieGameStand",
        "15" to "Fanatical",
        "16" to "Gamesrocket",
        "17" to "Games Republi",
        "18" to "SilaGames",
        "19" to "Playfield",
        "20" to "ImperialGames",
        "21" to "WinGameStore",
        "22" to "FunStockDigital",
        "23" to "GameBillet",
        "24" to "Voidu",
        "25" to "Epic Games Stor",
        "26" to "Razer Game Store",
        "27" to "Gamesplanet",
        "28" to "Gamesload",
        "29" to "2Game",
        "30" to "IndieGala",
        "31" to "Blizzard Shop",
        "32" to "AllYouPlay",
        "33" to "DLGamer",
        "34" to "Noctre",
        "35" to "DreamGame"
    )
}
