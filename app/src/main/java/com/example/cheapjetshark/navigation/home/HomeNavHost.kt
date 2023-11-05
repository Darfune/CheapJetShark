package com.example.cheapjetshark.navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.details.DetailsScreen
import com.example.cheapjetshark.screens.details.DetailsViewModel
import com.example.cheapjetshark.screens.main.favorites.FavoritesScreen
import com.example.cheapjetshark.screens.main.home.HomeScreen
import com.example.cheapjetshark.screens.main.home.HomeViewModel
import com.example.cheapjetshark.screens.main.stores.StoresScreen
import com.example.cheapjetshark.screens.search.SearchScreen
import com.example.cheapjetshark.screens.search.SearchViewModel

@Composable
fun HomeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.HOME,
        modifier = modifier
    ) {
        navigation(
            startDestination = Home.route,
            route = NavigationGraph.HOME
        ) {
            composable(route = Home.route) {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(navController = navController, homeViewModel)
            }
            composable(route = Stores.route) {
                StoresScreen(navController = navController)
            }
            composable(route = Favorites.route) {
                FavoritesScreen(navController = navController)
            }
            composable(route = Search.route) {
                val searchViewModel = hiltViewModel<SearchViewModel>()
                SearchScreen(
                    navController = navController,
                    searchViewModel,
                    goToGameDetails = { gameID ->
                        navController.navigateToGameDetails(gameID)
                    }
                )
            }
        }
        composable(
            route = Details.routeWithArgs,
            arguments = Details.arguments
        ) { navBackStackEntry ->
            val gameID = navBackStackEntry.arguments?.getInt(Details.detailsTypeArg)
            val detailsViewModel = hiltViewModel<DetailsViewModel>()
            DetailsScreen(navController = navController, gameID = gameID, detailsViewModel)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }


private fun NavHostController.navigateToGameDetails(accountType: Int) {
    this.navigateSingleTopTo("${Details.route}/$accountType")
}