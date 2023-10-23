package com.example.cheapjetshark.screens.auth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.navigation.start.AuthScreens
import com.example.cheapjetshark.screens.auth.components.AuthButton
import com.example.cheapjetshark.screens.auth.components.EmailTextField
import com.example.cheapjetshark.screens.auth.components.PasswordTextField
import com.example.cheapjetshark.screens.auth.components.SignInOrLogInText
import com.example.cheapjetshark.screens.auth.components.TopSection
import com.example.cheapjetshark.utils.rememberImeState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    loading: Boolean = false,
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }


    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val rePassword = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val isError = rememberSaveable {
        mutableStateOf(false)
    }
    val (passwordFocusRequest, rePasswordFocusRequest, buttonFocusRequest) = remember {
        FocusRequester.createRefs()
    }

    val valid = remember(email.value, password.value, rePassword.value) {
        email.value.trim().isNotEmpty() && password.value.trim()
            .isNotEmpty() && rePassword.value.trim()
            .isNotEmpty() && password.value.trim() == rePassword.value.trim()
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        color = MaterialTheme.colorScheme.background
    ) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusProperties { next = passwordFocusRequest },
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
                        .focusRequester(passwordFocusRequest)
                        .focusProperties { next = rePasswordFocusRequest },
                    passwordState = password,
                    isError = isError.value,
                    loading = loading,
                    label = "Password",
                    valid = valid,
                    imeAction = ImeAction.Next,
                    passwordVisibility = passwordVisibility,
                    focusRequester = rePasswordFocusRequest
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .focusRequester(rePasswordFocusRequest)
                        .focusProperties { next = buttonFocusRequest },
                    passwordState = rePassword,
                    isError = rePassword.value != password.value,
                    label = "re: Password",
                    loading = loading,
                    passwordVisibility = passwordVisibility,
                    valid = valid,
                    imeAction = ImeAction.Go,
                    focusRequester = buttonFocusRequest
                )
                Spacer(modifier = Modifier.height(16.dp))
                AuthButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(buttonFocusRequest),
                    loading = loading,
                    buttonText = "Log In",
                    validInputs = valid
                ) {
                    if (rePassword.value == password.value) {
                        viewModel.createUserWithEmailAndPass(
                            email = email.value.trim(),
                            password = password.value.trim()
                        ) {
                            navController.navigate(NavigationGraph.HOME) {
                                popUpTo(NavigationGraph.AUTH) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                SignInOrLogInText(
                    modifier = Modifier.fillMaxWidth(),
                    unClickableText = "Already have an account, ",
                    clickableText = "Log in now!"
                ) {
                    navController.navigate(AuthScreens.LogInScreen.name)
                }
            }
        }
    }
}
