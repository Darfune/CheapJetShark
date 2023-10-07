package com.example.cheapjetshark

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.navigation.root.CheapJetSharkNavigation
import com.example.cheapjetshark.ui.theme.CheapJetSharkTheme
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheapJetSharkTheme {
                Column {
                    CheapJetSharkNavigation(navController = rememberNavController())
                }
            }
        }
    }
}