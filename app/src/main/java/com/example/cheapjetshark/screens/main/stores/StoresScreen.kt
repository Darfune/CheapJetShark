package com.example.cheapjetshark.screens.main.stores

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cheapjetshark.screens.main.MainViewModel

@Composable
fun StoresScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Text(text = "Stores Screen")
}