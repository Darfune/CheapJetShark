@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cheapjetshark.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cheapjetshark.R
import com.example.cheapjetshark.navigation.CheapJetSharkScreens
import com.example.cheapjetshark.screens.main.components.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = R.drawable.selected_home_nav_item,
            unselectedIcon = R.drawable.unselected_home_nav_item,
            hasNews = false,
            screenToNav = CheapJetSharkScreens.SettingsScreen.name
        ),
        BottomNavigationItem(
            title = "Chat",
            selectedIcon = R.drawable.selected_store_nav_item,
            unselectedIcon = R.drawable.unselected_store_nav_item,
            hasNews = false,
            screenToNav = CheapJetSharkScreens.StoreScreen.name
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = R.drawable.selected_settings_nav_item,
            unselectedIcon = R.drawable.unselected_settings_nav_item,
            hasNews = false,
            screenToNav = CheapJetSharkScreens.SettingsScreen.name
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
//        modifier =,
            topBar = {},
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                                unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                unselectedTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
//                                navController.navigate(item.screenToNav)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (index == selectedItemIndex)
                                                item.selectedIcon
                                            else item.unselectedIcon
                                        ),
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            },
//        snackbarHost = {},
//        floatingActionButton = {},
//        floatingActionButtonPosition =,
//        containerColor =,
//        contentColor =,
//        contentWindowInsets =
        ) {

        }
    }
}

