package com.example.cheapjetshark.screens.main.home

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cheapjetshark.data.DataOrException
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.models.deals.DealsListItem
import com.example.cheapjetshark.screens.main.MainViewModel
import com.example.cheapjetshark.screens.main.home.components.DealListItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    Surface(modifier = Modifier.fillMaxSize()) {


//        DealListItem()
        NewestDealsArea(navController, listOfDeals = viewModel.newest20DealsList.value?.data) {

        }
    }
}

@Composable
fun NewestDealsArea(
    navController: NavController,
    listOfDeals: DealsList?,
    onDealPressed: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column {
        TitleSection(label = "Newest Deals")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .horizontalScroll(scrollState)
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
        color = MaterialTheme.colorScheme.onBackground
    )
}

