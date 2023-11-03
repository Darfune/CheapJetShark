package com.example.cheapjetshark.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cheapjetshark.navigation.bottombar.Details
import com.example.cheapjetshark.navigation.home.navigateSingleTopTo
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.search.components.BackIconButton
import com.example.cheapjetshark.screens.search.components.GamesSearchBar
import com.example.cheapjetshark.screens.search.components.SearchGameRow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel(),
    goToGameDetails: (Int) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchItem = rememberSaveable {
        mutableStateOf("")
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Top) {
            Row(
                modifier = Modifier.padding(start = 8.dp, top = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackIconButton(navController)
                GamesSearchBar(searchItem) {
                    keyboardController?.hide()
                    viewModel.getSearchedTitle(searchItem.value)
                    searchItem.value = ""
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            GamesList(navController, viewModel, goToGameDetails)
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun GamesList(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel(),
    goToGameDetails: (Int) -> Unit = {}
) {
    val listOfGames = viewModel.gamesFound
    if (viewModel.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)) {
            Log.d("In Lazy", "GamesList: $listOfGames")
            items(items = listOfGames) { game ->
                Log.d("Called", "GamesList: $game")
                SearchGameRow(game = game) {
                    goToGameDetails(game.gameID.toInt())
                }
            }
        }
    }
}


