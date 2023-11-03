@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cheapjetshark.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.R
import com.example.cheapjetshark.navigation.bottombar.Favorites
import com.example.cheapjetshark.navigation.bottombar.Home
import com.example.cheapjetshark.navigation.bottombar.HomeBottomNavigationScreens
import com.example.cheapjetshark.navigation.bottombar.Search
import com.example.cheapjetshark.navigation.bottombar.Stores
import com.example.cheapjetshark.navigation.home.HomeNavHost
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.main.components.FABContent
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraphBuilder.MainScreen(
    rootNavController: NavController
) {
    val homeNavController: NavHostController = rememberNavController()

    var showProfilePic by rememberSaveable {
        mutableStateOf(true)
    }
    val username = "User1"
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarScreenList = listOf(Home.route, Stores.route, Favorites.route)

    Scaffold(

        topBar = {
            if (bottomBarScreenList.contains(currentDestination?.route)){
                HomeTopBar(rootNavController, username, showProfilePic)
            }
        },
        bottomBar = {
            if (bottomBarScreenList.contains(currentDestination?.route)){
                HomeNavigationBottomBar(homeNavController, currentDestination)
            }


        },
        floatingActionButton = {
            if (Home.route == currentDestination?.route) FABContent {
                homeNavController.navigate(route = Search.route)
            }
        },
    ) { innerPadding ->
        HomeNavHost(
            navController = homeNavController,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeNavigationBottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    NavigationBar {
        HomeBottomNavigationScreens.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.5f
                    ),
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface
                ),
                selected = item.route == currentDestination?.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {  }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = (if (item.route == currentDestination?.route)
                                    item.selectedIcon
                                else item.unselectedIcon)!!
                            ),
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun HomeTopBar(rootNavController: NavController, username: String, showProfilePic: Boolean) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                if (showProfilePic) {
                    Card(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 0.dp), shape = RoundedCornerShape(50.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Profile picture"
                        )
                    }
                }
                Text(
                    text = username,
                    modifier = Modifier.padding(4.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    rootNavController.navigate(NavigationGraph.AUTH) {
                        popUpTo(NavigationGraph.HOME) {
                            inclusive = true
                        }
                    }
                }
            }) {
                Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Log Out")
            }
        }
    )
}
