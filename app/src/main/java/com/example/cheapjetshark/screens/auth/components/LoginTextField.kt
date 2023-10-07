package com.example.cheapjetshark.screens.auth.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    label: String,
    trailing: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        },

        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            focusedLabelColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            textColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
//            containerColor = MaterialTheme.colorScheme.primary.copy(alp)

        ),
        enabled = true,
        readOnly = false,
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {},
            onGo = {},
            onNext = {},
            onPrevious = {},
            onSearch = {},
            onSend = {}

        ),
        singleLine = false,
        maxLines = 1

    )
}