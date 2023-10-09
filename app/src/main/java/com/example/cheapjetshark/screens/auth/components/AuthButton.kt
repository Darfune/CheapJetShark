package com.example.cheapjetshark.screens.auth.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    loading: Boolean = false,
    validInputs: Boolean = false,
    action: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        shape = RoundedCornerShape(size = 4.dp),
        enabled = !loading && validInputs
    ) {
        if (loading) CircularProgressIndicator(modifier = Modifier.size(26.dp))
        else Text(text = buttonText)
    }
}