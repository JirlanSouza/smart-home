package com.js.smarthome.authentication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.js.smarthome.authentication.ui.components.PasswordField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    uiState: AuthenticationUiState,
    onUserNameChange: (userName: String) -> Unit,
    onUserPasswordChange: (password: String) -> Unit,
    onLogin: () -> Unit,
) {
    val passwordFocusRequester = FocusRequester()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 32.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        OutlinedTextField(
            singleLine = true,
            value = uiState.userName,
            isError = uiState.userNameError,
            onValueChange = { onUserNameChange(it) },
            label = { Text("User name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onDone = { passwordFocusRequester.requestFocus() },
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = uiState.password,
            isError = uiState.passwordError,
            label = "Password",
            onValueChange = { onUserPasswordChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            enabled = !uiState.isLoading && !uiState.userNameError && !uiState.passwordError,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { onLogin() }
        ) {
            Text("Entrar")
        }
    }
}