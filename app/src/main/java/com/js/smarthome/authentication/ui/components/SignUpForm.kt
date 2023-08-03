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
fun SignUpForm(
    name: String,
    userName: String,
    password: String,
    confirmPassword: String,
    nameError: Boolean,
    userNameError: Boolean,
    passwordError: Boolean,
    confirmPasswordError: Boolean,
    isLoading: Boolean,
    onNameChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    signIn: () -> Unit,
) {
    val userNameFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()
    val confirmPasswordFocusRequester = FocusRequester()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_form_title),
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(72.dp))

        OutlinedTextField(
            singleLine = true,
            value = name,
            isError = nameError,
            onValueChange = { onNameChange(it) },
            label = { Text(stringResource(id = R.string.user_name_field_label)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onDone = { userNameFocusRequester.requestFocus() },
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            singleLine = true,
            value = userName,
            isError = userNameError,
            onValueChange = { onUserNameChange(it) },
            label = { Text(stringResource(id = R.string.account_user_name_field_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(userNameFocusRequester),
            keyboardActions = KeyboardActions(
                onDone = { passwordFocusRequester.requestFocus() },
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = password,
            isError = passwordError,
            label = stringResource(id = R.string.password_field_label),
            onValueChange = { onPasswordChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            keyboardActions = KeyboardActions(
                onDone = { confirmPasswordFocusRequester.requestFocus() }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = confirmPassword,
            isError = confirmPasswordError,
            label = stringResource(id = R.string.confirm_password_field_label),
            onValueChange = { onConfirmPasswordChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(confirmPasswordFocusRequester)
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            enabled = !isLoading && !userNameError && !passwordError,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { signIn() }
        ) {
            Text(stringResource(id = R.string.sign_up_button_text))
        }
    }
}