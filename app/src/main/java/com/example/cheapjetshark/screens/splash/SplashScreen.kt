package com.example.cheapjetshark.screens.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.cheapjetshark.navigation.start.AuthScreens

@Composable
fun SplashScreen(navController: NavController) {
    Text(text = "Splash Screen")
    navController.navigate(AuthScreens.LogInScreen.name){
        popUpTo(navController.graph.id){
            inclusive = true
        }
    }
}