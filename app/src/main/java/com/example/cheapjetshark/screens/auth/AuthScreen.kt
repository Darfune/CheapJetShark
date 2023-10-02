package com.example.cheapjetshark.screens.auth

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cheapjetshark.navigation.root.NavigationGraph

@Composable
fun AuthScreen(navController: NavController) {
    Text(text = "Auth Screen")
    Button(
        onClick = {
            navController.popBackStack()
            navController.navigate(NavigationGraph.HOME)
        },
    ) {
        Text(text = "login")
    }

}