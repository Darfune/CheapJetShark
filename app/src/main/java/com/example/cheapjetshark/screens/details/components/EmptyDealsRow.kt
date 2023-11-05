package com.example.cheapjetshark.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun EmptyDealsRow() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        tonalElevation = 3.dp,
        shape = RoundedCornerShape(8.dp)
    )
    {
        Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.Top) {
            Surface(tonalElevation = 6.dp, shape = RoundedCornerShape(8.dp)) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    tonalElevation = 8.dp
                ){}
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(modifier = Modifier.padding(end = 8.dp)) {
                }
            }
        }
    }
}