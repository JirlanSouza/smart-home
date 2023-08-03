package com.js.smarthome.authentication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.js.smarthome.R
import com.js.smarthome.authentication.ui.components.SignInForm
import com.js.smarthome.authentication.ui.components.SignUpForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    uiState: AuthenticationUiState,
    onUserNameChange: (userName: String) -> Unit,
    onUserPasswordChange: (password: String) -> Unit,
    signIn: () -> Unit,
) {
    var signInMode by remember { mutableStateOf(true) }
    val sigInModeButtonText = stringResource(
        id = if (signInMode) R.string.sign_in_mode_button_text
        else R.string.sign_up_mode_button_text
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (signInMode) {
            SignInForm(
                userName = uiState.userName,
                password = uiState.password,
                userNameError = uiState.userNameError,
                passwordError = uiState.passwordError,
                isLoading = uiState.isLoading,
                onUserNameChange = onUserNameChange,
                onUserPasswordChange = onUserPasswordChange,
                signIn = signIn
            )

        } else {
            SignUpForm(
                name = "",
                userName = "",
                password = "",
                confirmPassword = "",
                nameError = false,
                userNameError = false,
                passwordError = false,
                confirmPasswordError = false,
                isLoading = false,
                onNameChange = {},
                onUserNameChange = {},
                onPasswordChange = {},
                onConfirmPasswordChange = {}
            ) {

            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 64.dp)
        ) {
            Divider(modifier = Modifier.padding(all = 8.dp))

            TextButton(onClick = { signInMode = !signInMode }) {
                Text(
                    text = sigInModeButtonText,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}