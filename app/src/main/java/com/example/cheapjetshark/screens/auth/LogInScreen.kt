@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.cheapjetshark.screens.auth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.navigation.start.AuthScreens
import com.example.cheapjetshark.screens.auth.components.AuthButton
import com.example.cheapjetshark.screens.auth.components.EmailTextField
import com.example.cheapjetshark.screens.auth.components.ForgotPasswordText
import com.example.cheapjetshark.screens.auth.components.PasswordTextField
import com.example.cheapjetshark.screens.auth.components.SignInOrLogInText
import com.example.cheapjetshark.screens.auth.components.TopSection


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LogInScreen(
    navController: NavController,
    loading: Boolean = false,
    isCreatingAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email, password ->
        Log.d(
            "Form",
            "LogInScreen: $email $password"
        )
    }
) {

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
    val passwordFocusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    LogInBody(
        navController = navController,
        email = email,
        password = password,
        passwordVisibility = passwordVisibility,
        isError = isError,
        passwordFocusRequest = passwordFocusRequest,
        keyboardController = keyboardController,
        valid = valid,
        loading = loading,
        onDone = onDone
    )
}

@Composable
fun LogInBody(
    navController: NavController = rememberNavController(),
    email: MutableState<String>,
    password: MutableState<String>,
    passwordVisibility: MutableState<Boolean>,
    isError: MutableState<Boolean>,
    passwordFocusRequest: FocusRequester,
    keyboardController: SoftwareKeyboardController?,
    valid: Boolean,
    onDone: (String, String) -> Unit,
    loading: Boolean
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
                    loading = loading,
                    isError = isError.value,
                    imeAction = ImeAction.Next,
                    focusRequester = passwordFocusRequest,

                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequest),
                    emailState = email,
                    passwordState = password,
                    isError = isError.value,
                    label = "Password",
                    loading = loading,
                    passwordVisibility = passwordVisibility,
                    valid = valid,
                    imeAction = ImeAction.Go,
                    onDone = onDone,
                    focusRequester = passwordFocusRequest
                )
                Spacer(modifier = Modifier.height(16.dp))
                AuthButton(
                    modifier = Modifier.fillMaxWidth(),
                    loading = loading,
                    buttonText = "Log In",
                    validInputs = valid
                ) {
                    onDone(email.value.trim(), password.value.trim())
                    keyboardController?.hide()
                    navController.navigate(NavigationGraph.HOME) {
                        popUpTo(NavigationGraph.AUTH) {
                            inclusive = true
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                SignInOrLogInText(
                    modifier = Modifier.fillMaxWidth(),
                    unClickableText = "Don't have an account, ",
                    clickableText = "Sign in now!"
                ) {
                    navController.navigate(AuthScreens.RegistrationScreen.name)
                }
                Spacer(modifier = Modifier.height(8.dp))
                ForgotPasswordText(modifier = Modifier.fillMaxWidth()) {
                    navController.navigate(AuthScreens.ResetPasswordScreen.name)
                }

            }
        }
    }
}