package com.example.cheapjetshark.navigation.root

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cheapjetshark.navigation.auth.authNavGraph
import com.example.cheapjetshark.navigation.home.MainScreen
import com.example.cheapjetshark.screens.details.DetailsScreen
import com.example.cheapjetshark.screens.search.SearchScreen
import com.example.cheapjetshark.screens.search.SearchViewModel
import com.example.cheapjetshark.screens.splash.SplashScreen
@Composable
fun CheapJetSharkNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationGraph.ROOT,
        startDestination = NavigationGraph.SPLASH
    ) {
        composable(route = NavigationGraph.SPLASH) {
            SplashScreen(navController = navController)
        }
        authNavGraph(navController = navController)
        composable(route = NavigationGraph.HOME) {
            MainScreen(rootNavController = navController)
        }
        composable(route = NavigationGraph.DETAILS){
            DetailsScreen(navController = navController)
        }
        composable(route = NavigationGraph.SEARCH){
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(navController = navController, searchViewModel)
        }
    }
}

object NavigationGraph {
    const val ROOT = "root_graph"
    const val SPLASH = "splash_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
    const val SEARCH = "search_graph"
}

