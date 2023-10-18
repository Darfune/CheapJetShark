@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cheapjetshark.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Badge
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.R
import com.example.cheapjetshark.navigation.bottombar.HomeNavGraph
import com.example.cheapjetshark.navigation.bottombar.MainNavigationScreens
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.main.components.FABContent
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel()
) {

    val items = listOf(
        MainNavigationScreens.Main,
        MainNavigationScreens.Stores,
        MainNavigationScreens.Favorites,
    )
    var showProfilePic by rememberSaveable {
        mutableStateOf(true)
    }
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val username = "User1"
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = items.any { it.name == currentDestination?.route }


    Scaffold(
        topBar = {
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

                        }
                    }) {
                        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Log Out")
                    }
                }
            )
        },
        bottomBar = {
            if (bottomBarDestination) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
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
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.name) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
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
            }
        },
//        snackbarHost = {},
        floatingActionButton = { FABContent() },
    ) {
        HomeNavGraph(navController = navController)
    }
}