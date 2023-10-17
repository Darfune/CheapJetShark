package com.example.cheapjetshark.screens.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.navigation.start.AuthScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SplashScreen(navController: NavController) {
    Text(text = "Splash Screen")

    //Todo: Check if there's a user, if so take them to Home Screen, otherwise, to AuthScreens
    if (FirebaseAuth.getInstance().currentUser!!.email.isNullOrEmpty()){
        navController.navigate(AuthScreens.LogInScreen.name){
            popUpTo(navController.graph.id){
                inclusive = true
            }
        }
    } else {
        navController.navigate(NavigationGraph.HOME) {
            popUpTo(NavigationGraph.AUTH) {
                inclusive = true
            }
        }
    }


}