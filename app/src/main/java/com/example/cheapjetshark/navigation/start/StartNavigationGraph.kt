package com.example.cheapjetshark.navigation.start

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.auth.AuthScreen
import com.example.cheapjetshark.screens.main.MainScreen
import com.example.cheapjetshark.screens.main.MainViewModel
import com.example.cheapjetshark.screens.splash.SplashScreen

fun NavGraphBuilder.startNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationGraph.START,
        startDestination = CheapJetSharkScreens.SplashScreen.name
    ) {
        composable(CheapJetSharkScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(CheapJetSharkScreens.AuthScreen.name){
            AuthScreen(navController = navController)
        }

    }
}