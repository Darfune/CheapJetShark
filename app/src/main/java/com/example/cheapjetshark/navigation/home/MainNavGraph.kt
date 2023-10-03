package com.example.cheapjetshark.navigation.bottombar

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.main.MainViewModel
import com.example.cheapjetshark.screens.main.home.HomeScreen
import com.example.cheapjetshark.screens.main.settings.SettingsScreen
import com.example.cheapjetshark.screens.main.stores.StoresScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationGraph.HOME,
        startDestination = MainNavigationScreens.Main.name
    ) {
        composable(route = MainNavigationScreens.Main.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            HomeScreen(navController = navController, mainViewModel)
        }
        composable(route = MainNavigationScreens.Stores.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            StoresScreen(navController = navController, mainViewModel)
        }
        composable(route = MainNavigationScreens.Settings.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            SettingsScreen(navController = navController, mainViewModel)
        }
    }
}