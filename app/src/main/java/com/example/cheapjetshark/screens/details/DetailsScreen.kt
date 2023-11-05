package com.example.cheapjetshark.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cheapjetshark.R
import com.example.cheapjetshark.screens.details.components.DealsRow
import com.example.cheapjetshark.screens.details.components.EmptyDealsRow
import com.example.cheapjetshark.ui.theme.onSaleColor

@Composable
fun DetailsScreen(
    navController: NavHostController = rememberNavController(),
    gameID: Int?,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    viewModel.getGameDetails(gameID)
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxHeight(.3f)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = viewModel.gameDetails.info.thumb,
                    placeholder = painterResource(R.drawable.ic_temp_game_placeholder),
                    contentDescription = "Game Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            viewModel.gameDetails.info.let {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.titleLarge,
                    softWrap = true,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("Lowest recorded price: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = onSaleColor
                        )
                    ) {
                        append(viewModel.gameDetails.cheapestPriceEver.price)
                    }
                },
                style = MaterialTheme.typography.titleSmall,
                softWrap = true,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 8.dp, top = 16.dp)
            )
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 8.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    ), tonalElevation = 1.dp
            ) {
                val deals = viewModel.gameDetails.deals
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(items = deals) { deal ->
                        DealsRow(deal)
                    }
                }
            }
        }
    }
}


