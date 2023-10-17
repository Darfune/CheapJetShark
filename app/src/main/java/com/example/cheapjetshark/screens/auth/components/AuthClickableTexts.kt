package com.example.cheapjetshark.screens.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle

@Composable
fun ForgotPasswordText(modifier: Modifier, resetPasswordAction: (Int) -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        ClickableText(
            text = AnnotatedString(
                "Forgot the password?",
                SpanStyle(color = MaterialTheme.colorScheme.primary)
            ),
            onClick = resetPasswordAction
        )
    }
}

@Composable
fun SignInOrLogInText(
    modifier: Modifier = Modifier,
    unClickableText: String,
    clickableText: String,
    signInAction: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = unClickableText,
            color = MaterialTheme.colorScheme.onBackground,
        )
        ClickableText(
            text = AnnotatedString(
                clickableText,
                SpanStyle(color = MaterialTheme.colorScheme.primary)
            ),
            onClick = signInAction
        )
    }
}