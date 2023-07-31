package com.js.smarthome.authentication.ui.screens

data class AuthenticationUiState(
    val userName: String = "",
    val userNameError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val isLoading: Boolean = false,
    val error: Boolean = false,
)