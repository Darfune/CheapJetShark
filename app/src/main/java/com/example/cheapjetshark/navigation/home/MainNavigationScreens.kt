package com.example.cheapjetshark.navigation.bottombar

import com.example.cheapjetshark.R

sealed class MainNavigationScreens(
    val name: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null
) {
    data object Main : MainNavigationScreens(
        name = "home",
        title = "Main",
        selectedIcon = R.drawable.selected_home_nav_item,
        unselectedIcon = R.drawable.unselected_home_nav_item,
        hasNews = false
    )

    data object Stores : MainNavigationScreens(
        name = "stores",
        title = "Stores",
        selectedIcon = R.drawable.selected_store_nav_item,
        unselectedIcon = R.drawable.unselected_store_nav_item,
        hasNews = false
    )

    data object Favorites : MainNavigationScreens(
        name = "favorites",
        title = "Favorites",
        selectedIcon = R.drawable.selected_favorites_item,
        unselectedIcon = R.drawable.unselected_favorites_item,
        hasNews = false
    )
}


