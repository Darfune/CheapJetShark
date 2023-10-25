package com.example.cheapjetshark.screens.main.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cheapjetshark.data.DataOrException
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.screens.main.MainViewModel
import com.example.cheapjetshark.screens.main.home.components.DealListItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val columnScrollState = rememberScrollState()

    if (viewModel.newest20DealsList.value?.data.isNullOrEmpty()){
        viewModel.getNewest20DealsList()
    }

    if (viewModel.topDealsList.value?.data.isNullOrEmpty()){
        viewModel.getTopDealsList()
    }

    if (viewModel.topGamesDealsList.value?.data.isNullOrEmpty()){
        viewModel.getTopGamesDealsList()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(columnScrollState)) {

            NewestDealsArea(
                navController,
                listOfDeals = viewModel.newest20DealsList.value?.data,
                loading = viewModel.newest20DealsList.value?.loading
            ) {
            }
            TopDealsArea(
                navController,
                listOfDeals = viewModel.topDealsList.value?.data,
                loading = viewModel.topDealsList.value?.loading
            ) {

            }
            TopGamesDealsArea(
                navController,
                listOfDeals = viewModel.topGamesDealsList.value?.data,
                loading = viewModel.topGamesDealsList.value?.loading
            ) {

            }
        }
    }
}

@Composable
fun TopGamesDealsArea(
    navController: NavController,
    listOfDeals: DealsList?,
    loading: Boolean?,
    onDealPressed: (String) -> Unit
) {
    val scrollState3 = rememberScrollState()
    Column {
        TitleSection(label = "Top Games with Deals")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState3)
        ) {
            if (!listOfDeals.isNullOrEmpty()) {
                for (i in 0..<listOfDeals.size) {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        DealListItem(modifier = Modifier, deal = listOfDeals[i]) {
                            onDealPressed(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopDealsArea(
    navController: NavController,
    listOfDeals: DealsList?,
    loading: Boolean?,
    onDealPressed: (String) -> Unit
) {
    val scrollState2 = rememberScrollState()
    Column {
        TitleSection(label = "Top Rated Deals")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState2)
        ) {
            if (!listOfDeals.isNullOrEmpty()) {
                for (i in 0..<listOfDeals.size) {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        DealListItem(modifier = Modifier, deal = listOfDeals[i]) {
                            onDealPressed(it)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NewestDealsArea(
    navController: NavController,
    listOfDeals: DealsList?,
    loading: Boolean?,
    onDealPressed: (String) -> Unit
) {
    val scrollState1 = rememberScrollState()
    Column {
        TitleSection(label = "Newest Deals")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState1)
        ) {
            if (!listOfDeals.isNullOrEmpty()) {
                for (i in 0..<listOfDeals.size) {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        DealListItem(modifier = Modifier, deal = listOfDeals[i]) {
                            onDealPressed(it)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TitleSection(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(start = 16.dp)
    )
}

