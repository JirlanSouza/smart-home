package com.js.smarthome.authentication.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.js.smarthome.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    label: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var isVisibleValue by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        isError = isError,
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = { isVisibleValue = !isVisibleValue }, content = {
                Icon(
                    painter = painterResource(
                        id = if (isVisibleValue) R.drawable.round_visibility_off_24
                        else R.drawable.round_visibility_24
                    ),
                    contentDescription = "password visibility",
                )
            })
        },
        singleLine = true,
        onValueChange = onValueChange,
        visualTransformation = (
                if (isVisibleValue) VisualTransformation.None else PasswordVisualTransformation()
                ),
        modifier = modifier,
        keyboardActions = keyboardActions
    )
}
