package com.example.cheapjetshark.screens.auth.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    label: String,

    enable: Boolean,
    isSingleLine: Boolean = true,
    isError: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)?
) {
    TextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
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
        trailingIcon = trailingIcon,
        enabled = enable,
        readOnly = false,
        visualTransformation = visualTransformation,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        maxLines = 1
    )
}