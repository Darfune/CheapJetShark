package com.example.cheapjetshark.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.screens.splash.SplashScreen

@Composable
fun CheapJetSharkNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CheapJetSharkScreens.SplashScreen.name
    ) {
        composable(CheapJetSharkScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }
}