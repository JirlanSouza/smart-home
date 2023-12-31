package com.js.smarthome.authentication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.js.smarthome.R

@ExperimentalMaterial3Api
@Composable
fun SignInForm(
    userName: String,
    password: String,
    userNameError: Boolean,
    passwordError: Boolean,
    isLoading: Boolean,
    onUserNameChange: (userName: String) -> Unit,
    onUserPasswordChange: (password: String) -> Unit,
    signIn: () -> Unit,
) {
    val passwordFocusRequester = FocusRequester()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_form_title),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(72.dp))

        OutlinedTextField(
            singleLine = true,
            value = userName,
            isError = userNameError,
            onValueChange = { onUserNameChange(it) },
            label = { Text(stringResource(id = R.string.account_user_name_field_label)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onDone = { passwordFocusRequester.requestFocus() },
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = password,
            isError = passwordError,
            label = stringResource(id = R.string.password_field_label),
            onValueChange = { onUserPasswordChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            enabled = !isLoading && !userNameError && !passwordError,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { signIn() }
        ) {
            Text(stringResource(id = R.string.sign_in_button_text))
        }
    }
}