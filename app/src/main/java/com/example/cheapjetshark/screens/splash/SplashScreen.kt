package com.example.cheapjetshark.screens.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cheapjetshark.navigation.CheapJetSharkScreens

@Composable
fun SplashScreen(navController: NavController) {
    navController.navigate(CheapJetSharkScreens.MainScreen.name)
}