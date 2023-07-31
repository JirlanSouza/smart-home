package com.js.smarthome.main.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.js.smarthome.authentication.domain.entity.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    userProfile: UserProfile?,
    logoutAction: () -> Unit,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Home") },
                actions = {
                    Button(onClick = logoutAction) {
                        userProfile?.let { it1 -> Text(it1.name) }
                    }
                }
            )
        },
        content = { content(it) }
    )
}