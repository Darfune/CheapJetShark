package com.example.cheapjetshark.navigation.bottombar

import com.example.cheapjetshark.R

sealed class HomeNavigationScreens(
    val name: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null
) {
    data object Home : HomeNavigationScreens(
        name = "home",
        title = "Home",
        selectedIcon = R.drawable.selected_home_nav_item,
        unselectedIcon = R.drawable.unselected_home_nav_item,
        hasNews = false
    )

    data object Stores : HomeNavigationScreens(
        name = "stores",
        title = "Stores",
        selectedIcon = R.drawable.selected_store_nav_item,
        unselectedIcon = R.drawable.unselected_store_nav_item,
        hasNews = false
    )

    data object Favorites : HomeNavigationScreens(
        name = "favorites",
        title = "Favorites",
        selectedIcon = R.drawable.selected_favorites_item,
        unselectedIcon = R.drawable.unselected_favorites_item,
        hasNews = false
    )
}


