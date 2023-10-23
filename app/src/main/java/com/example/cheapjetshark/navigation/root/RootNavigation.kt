package com.example.cheapjetshark.navigation.root

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.navigation.start.authNavGraph
import com.example.cheapjetshark.navigation.home.MainScreen
import com.example.cheapjetshark.repository.ApiRepository
import com.example.cheapjetshark.screens.main.MainViewModel
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

    }
}

object NavigationGraph {
    const val ROOT = "root_graph"
    const val SPLASH = "splash_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
}

