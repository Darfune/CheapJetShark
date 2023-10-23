@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.cheapjetshark.screens.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cheapjetshark.R
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.navigation.start.AuthScreens
import com.example.cheapjetshark.screens.auth.components.AuthButton
import com.example.cheapjetshark.screens.auth.components.EmailTextField
import com.example.cheapjetshark.screens.auth.components.ForgotPasswordText
import com.example.cheapjetshark.screens.auth.components.PasswordTextField
import com.example.cheapjetshark.screens.auth.components.SignInOrLogInText
import com.example.cheapjetshark.screens.auth.components.TopSection
import com.example.cheapjetshark.utils.Constants


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LogInScreen(
    navController: NavController,
    loading: Boolean = false,
    viewModel: AuthViewModel = hiltViewModel(),
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
    val authErrorMessageVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val authErrorMessage = remember {
        mutableStateOf("")
    }
    val (passwordFocusRequest) = remember { FocusRequester.createRefs() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

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
                if (authErrorMessageVisibility.value){
                    DisplayErrorMessage(authErrorMessage.value)
                }
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
                    passwordState = password,
                    isError = isError.value,
                    label = "Password",
                    loading = loading,
                    passwordVisibility = passwordVisibility,
                    valid = valid,
                    imeAction = ImeAction.Go
                )
                Spacer(modifier = Modifier.height(16.dp))
                AuthButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    loading = loading,
                    buttonText = "Log In",
                    validInputs = valid
                ) {
                    keyboardController?.hide()
                    viewModel.signInWithEmailAndPass(
                        email = email.value.trim(),
                        password = password.value.trim()
                    ) {
                        if (viewModel.loading.value == false && viewModel.authSuccess.value == true) {
                            navController.navigate(NavigationGraph.HOME) {
                                popUpTo(NavigationGraph.AUTH) {
                                    inclusive = true
                                }
                            }
                        } else if (viewModel.loading.value == false && viewModel.loading.value == false) {
                            email.value = ""
                            password.value = ""
                            authErrorMessage.value = viewModel.fbError.value.toString()
                            authErrorMessageVisibility.value = true
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

@Composable
fun DisplayErrorMessage(error: String) {
    Spacer(modifier = Modifier.height(16.dp))

    Log.d("FB ERROR", "LogInScreen: $error")
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(
            id = when (error) {
                Constants.FB_NETWORK_ERROR -> R.string.fb_net_error
                Constants.FB_WRONG_CREDENTIALS ->
                    R.string.credentials_don_t_must_try_again

                Constants.FB_BADLY_FORMATTED_EMAIL ->
                    R.string.the_email_address_is_badly_formatted

                else -> R.string.unknown_error_occurred
            }
        ),
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.error
    )
    Spacer(modifier = Modifier.height(4.dp))

}