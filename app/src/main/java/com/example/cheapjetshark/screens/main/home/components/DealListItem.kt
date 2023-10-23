package com.example.cheapjetshark.screens.main.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cheapjetshark.R
import com.example.cheapjetshark.models.deals.DealsListItem
import com.example.cheapjetshark.ui.theme.notOnSaleColor
import com.example.cheapjetshark.ui.theme.onSaleColor

@Composable
fun DealListItem(
    deal: DealsListItem, onPressDeal: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val screenWidth =
        context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val spacing = 10.dp
    Surface(
        shape = RoundedCornerShape(29.dp),
        tonalElevation = 1.dp,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable { onPressDeal.invoke(deal.dealID) }
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                AsyncImage(
                    model = deal.thumb,
                    placeholder = painterResource(R.drawable.ic_temp_game_placeholder),
                    contentDescription = "Game Image",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))
                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "favorite button"
                    )
                    DealRating(score = deal.dealRating)
                }
            }
            Text(
                text = deal.title,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(modifier = Modifier.padding(4.dp)) {
                if (deal.isOnSale == "1") {
                    Text(text = "$" + deal.normalPrice + " → ")
                    Text(text = "$" + deal.salePrice)
                } else {
                    Text(text = "$" + deal.normalPrice)
                }

            }
        }
        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom) {
            if (deal.isOnSale == "1")
                OnSaleRoundedIcon(text = "On Sale", color = onSaleColor)
            else
                OnSaleRoundedIcon(text = "Not on Sale", color = notOnSaleColor)
        }
    }
}

@Composable
fun OnSaleRoundedIcon(text: String, color: Color) {
    Surface(
        modifier = Modifier.clip(
            RoundedCornerShape(
                bottomEndPercent = 29,
                topStartPercent = 29
            )
        ),
        tonalElevation = 8.dp,
        color = color
    ) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .heightIn(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun DealRating(score: String) {
    Surface(
        modifier = Modifier
            .padding(4.dp), shape = RoundedCornerShape(56.dp),
        tonalElevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier.padding(3.dp)
            )
            Text(text = score, style = MaterialTheme.typography.labelLarge)
        }
    }
}
