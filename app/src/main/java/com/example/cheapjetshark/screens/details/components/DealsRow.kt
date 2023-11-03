package com.example.cheapjetshark.screens.details.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cheapjetshark.R
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearchItem
import com.example.cheapjetshark.ui.theme.onSaleColor
import kotlin.math.floor

@Preview
@Composable
fun DealsRow() {

    Surface(
        modifier = Modifier
            .clickable
            { }
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        tonalElevation = 3.dp,
        shape = RoundedCornerShape(8.dp)
    )
    {
        Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.Top) {

            Surface(tonalElevation = 6.dp, shape = RoundedCornerShape(8.dp)) {
                AsyncImage(
                    model = "https://www.cheapshark.com/img/stores/logos/0.png",
                    placeholder = painterResource(R.drawable.ic_store_placeholder),
                    contentDescription = "Game Image",
                    contentScale = ContentScale.Crop,
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
                    text = "Steam",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(modifier = Modifier.padding(end = 8.dp)) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            ) {
                                append("19.99 → 4.99 - Down: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = onSaleColor
                                )
                            ) {
                                append(floor(78.839420).toString() + "%")
                            }

                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }


}