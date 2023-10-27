package com.example.cheapjetshark.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cheapjetshark.R
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearchItem

@Composable
fun SearchGameRow(game: GamesBySearchItem, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp),
        tonalElevation = 1.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.Top) {

            Surface(tonalElevation = 3.dp, shape = RoundedCornerShape(8.dp)) {
                AsyncImage(
                    model = game.thumb,
                    placeholder = painterResource(R.drawable.ic_temp_game_placeholder),
                    contentDescription = "Game Image",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = game.external,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = game.cheapest,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

