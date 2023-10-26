package com.example.cheapjetshark.screens.main.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cheapjetshark.R

@Composable
fun FABContent(onSearch: () -> Unit) {
    FloatingActionButton(
        onClick = onSearch ,
        shape = RoundedCornerShape(50.dp),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search Icon")
    }
}