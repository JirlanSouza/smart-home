package com.js.smarthome.authentication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.dp
import com.js.smarthome.authentication.ui.components.SignInForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    uiState: AuthenticationUiState,
    onUserNameChange: (userName: String) -> Unit,
    onUserPasswordChange: (password: String) -> Unit,
    signIn: () -> Unit,
) {
    var signInMode by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .offset(y = (-48).dp)
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

        }

        Divider(modifier = Modifier.padding(all = 16.dp))

        TextButton(onClick = { signInMode = !signInMode }) {
            Text(text = "Criar uma conta", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}