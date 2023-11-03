package com.example.cheapjetshark.screens.search.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.cheapjetshark.R
import com.example.cheapjetshark.navigation.bottombar.Home
import com.example.cheapjetshark.navigation.home.navigateSingleTopTo
import com.example.cheapjetshark.navigation.root.NavigationGraph

@Composable
fun BackIconButton(navController: NavHostController) {
    IconButton(
        onClick = {
            navController.navigateSingleTopTo(Home.route)
        },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "Exit search screen"
        )
    }
}