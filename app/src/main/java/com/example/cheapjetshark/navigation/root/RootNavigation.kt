package com.example.cheapjetshark.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cheapjetshark.navigation.start.authNavGraph
import com.example.cheapjetshark.screens.main.MainScreen

@Composable
fun CheapJetSharkNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationGraph.ROOT,
        startDestination = NavigationGraph.AUTH
    ) {
        authNavGraph(navController = navController)
        composable(route = NavigationGraph.HOME){
            MainScreen()
        }

    }
}

object NavigationGraph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
}