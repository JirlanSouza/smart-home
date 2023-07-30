package com.js.smarthome.authentication.domain.repositories

import com.js.smarthome.authentication.domain.entity.AuthenticationResult

interface AuthenticationRepository {
    fun authenticate(userName: String, password: String): AuthenticationResult
    fun logout(token: String): Unit
}