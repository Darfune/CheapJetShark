@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.cheapjetshark.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.R
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.navigation.start.AuthScreens
import com.example.cheapjetshark.screens.auth.components.LoginTextField


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreen(navController: NavController) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val isError = rememberSaveable {
        mutableStateOf(false)
    }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    LoginScreen(
        navController = navController,
        email = email,
        password = password,
        passwordVisibility = passwordVisibility,
        isError = isError,
        passwordFocusRequest = passwordFocusRequest,
        keyboardController = keyboardController,
        valid = valid
    )
}

@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
    email: MutableState<String>,
    password: MutableState<String>,
    passwordVisibility: MutableState<Boolean>,
    isError: MutableState<Boolean>,
    passwordFocusRequest: FocusRequester,
    keyboardController: SoftwareKeyboardController?,
    valid: Boolean
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSection()
            Spacer(modifier = Modifier.height(36.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                EmailTextField(
                    modifier = Modifier.fillMaxWidth(),
                    emailState = email,
                    label = "Email",
                    isError = isError.value,
                    imeAction = ImeAction.Next,
                    passwordFocusRequest = passwordFocusRequest
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequest),
                    passwordState = password,
                    isError = isError.value,
                    emailState = email,
                    label = "Password",
                    valid = valid,
                    passwordVisibility = passwordVisibility

                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(NavigationGraph.HOME) {
                            popUpTo(NavigationGraph.AUTH) {
                                inclusive = true
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    shape = RoundedCornerShape(size = 4.dp)
                ) {
                    Text(text = "Log In")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account, ",
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    ClickableText(
                        text = AnnotatedString(
                            "Sign in now!",
                            SpanStyle(color = MaterialTheme.colorScheme.primary)
                        ),

                        onClick = {
                            navController.navigate(AuthScreens.RegistrationScreen.name)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EmailTextField(
    modifier: Modifier,
    label: String,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    emailState: MutableState<String>,
    isError: Boolean,
    passwordFocusRequest: FocusRequester
) {
    LoginTextField(
        modifier = modifier,
        label = "Email",
        valueState = emailState,
        isError = isError,
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next,
        onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        },
        enable = true,
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
    passwordVisibility: MutableState<Boolean>,
    valid: Boolean
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
//            onDone(emailState.value.trim(), passwordState.value.trim())
        },
        enable = true,
        isSingleLine = true,
        visualTransformation = visualTransformation
    ) {
        PasswordVisibility(passwordVisibility = passwordVisibility)
    }
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = {passwordVisibility.value = !visible}) {
        Icons.Default.Close
    }
}

@Composable
private fun TopSection() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 50.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("C")
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 42.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("heap")
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 50.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            ) {
                append("JET")
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 50.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("S")
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 42.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("hark")
            }
        }
    )
    Row(
        modifier = Modifier.padding(top = 20.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.cheapsharklogo),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Logo"
            )
            Text(
                text = "Find the best prices",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}