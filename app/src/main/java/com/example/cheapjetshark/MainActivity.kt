package com.example.cheapjetshark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cheapjetshark.components.GameCard
import com.example.cheapjetshark.components.SearchItem
import com.example.cheapjetshark.ui.theme.CheapJetSharkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheapJetSharkTheme {
                Column {
                    SearchItem()
                    GameCard()
                }
            }
        }
    }
}