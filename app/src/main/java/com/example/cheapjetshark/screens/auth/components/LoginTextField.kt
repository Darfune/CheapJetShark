package com.example.cheapjetshark.screens.auth.components

import android.util.Log
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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

@Composable
fun EmailTextField(
    modifier: Modifier,
    label: String,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    emailState: MutableState<String>,
    isError: Boolean,
    loading: Boolean
) {
    LoginTextField(
        modifier = modifier,
        label = label,
        valueState = emailState,
        isError = isError,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction,
        enable = !loading,
        isSingleLine = true,
        trailingIcon = null
    )
}


@Composable
fun PasswordTextField(
    modifier: Modifier,
    emailState: MutableState<String>,
    passwordState: MutableState<String>,
    isError: Boolean,
    label: String,
    loading: Boolean,
    passwordVisibility: MutableState<Boolean>,
    valid: Boolean,
    onDone: (String, String) -> Unit
) {
    val visualTransformation =
        if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    LoginTextField(
        modifier = modifier,
        label = label,
        valueState = passwordState,
        isError = isError,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done,
        onAction = KeyboardActions {
            if (!valid) return@KeyboardActions
            onDone(emailState.value.trim(), passwordState.value.trim())
        },
        enable = !loading,
        isSingleLine = true,
        visualTransformation = visualTransformation
    ) {
        PasswordVisibility(passwordVisibility = passwordVisibility)
    }
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close
    }
}
