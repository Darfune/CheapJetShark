package com.example.cheapjetshark.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.screens.main.MainScreen
import com.example.cheapjetshark.screens.main.MainViewModel
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

        composable(CheapJetSharkScreens.MainScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, mainViewModel)
        }

    }
}