package com.example.cheapjetshark.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearch
import com.example.cheapjetshark.screens.search.components.BackIconButton
import com.example.cheapjetshark.screens.search.components.GamesSearchBar
import com.example.cheapjetshark.screens.search.components.SearchGameRow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchItem = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    viewModel.getSearchedTitleList(searchItem.value)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            GamesList(navController, viewModel.searchTitle.value?.data)
        }

    }

}

@Composable
fun GamesList(navController: NavHostController, gameList: GamesBySearch?) {

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)) {

//        items(items = gameList) { game ->
//            SearchGameRow(game) {
//                // TODO: navigate to the details screen
//            }
//        }

    }
}


