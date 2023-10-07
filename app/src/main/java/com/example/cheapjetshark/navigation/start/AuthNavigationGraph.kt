package com.example.cheapjetshark.navigation.start

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.auth.AuthScreen
import com.example.cheapjetshark.screens.auth.RegistrationScreen
import com.example.cheapjetshark.screens.splash.SplashScreen

enum class AuthScreens {
    AuthScreen,
    RegistrationScreen,
    SplashScreen
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationGraph.AUTH,
        startDestination = AuthScreens.SplashScreen.name
    ) {
        composable(AuthScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(AuthScreens.AuthScreen.name){
            AuthScreen(navController = navController)
        }
        composable(AuthScreens.RegistrationScreen.name){
            RegistrationScreen(navController = navController)
        }


    }
}