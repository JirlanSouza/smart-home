package com.js.smarthome.authentication.domain.entity

import com.js.smarthome.authentication.domain.error.AuthenticationError

data class AuthenticationResult(
    val success: Boolean,
    val error: AuthenticationError?,
    val token: String?,
    val user: UserProfile?,
)
