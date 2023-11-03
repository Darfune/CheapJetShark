package com.example.cheapjetshark.navigation.bottombar

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.cheapjetshark.R

interface HomeNavigationScreens {

    val route: String
    val title: String
    val selectedIcon: Int?
    val unselectedIcon: Int?
}

object Home : HomeNavigationScreens{
    override val route = "home"
    override val title = "Home"
    override val selectedIcon = R.drawable.selected_home_nav_item
    override val unselectedIcon = R.drawable.unselected_home_nav_item
}


object Stores : HomeNavigationScreens{
    override val route = "stores"
    override val title = "Stores"
    override val selectedIcon = R.drawable.selected_store_nav_item
    override val unselectedIcon = R.drawable.unselected_store_nav_item
}

object Favorites : HomeNavigationScreens{
    override val route = "favorites"
    override val title = "Favorites"
    override val selectedIcon = R.drawable.selected_favorites_item
    override val unselectedIcon = R.drawable.unselected_favorites_item

}

object Search : HomeNavigationScreens{
    override val route = "search"
    override val title = "Search"
    override val selectedIcon = null
    override val unselectedIcon = null

}

object Details : HomeNavigationScreens{
    override val route = "details"
    override val title = "Details"
    override val selectedIcon = null
    override val unselectedIcon = null
    const val detailsTypeArg = "gameID"
    val routeWithArgs = "${route}/{${detailsTypeArg}}"
    val arguments = listOf(
        navArgument(detailsTypeArg) { type = NavType.IntType }
    )
}

val HomeBottomNavigationScreens = listOf(Home, Stores, Favorites)