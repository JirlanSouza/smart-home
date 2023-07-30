package com.js.smarthome.authentication.data.repositories

import com.js.smarthome.authentication.domain.entity.AuthenticationResult
import com.js.smarthome.authentication.domain.entity.UserGroup
import com.js.smarthome.authentication.domain.entity.UserProfile
import com.js.smarthome.authentication.domain.repositories.AuthenticationRepository
import java.util.UUID
import javax.inject.Inject

class FakeAuthenticationRepository @Inject constructor() : AuthenticationRepository {
    override fun authenticate(userName: String, password: String): AuthenticationResult {
        return AuthenticationResult(
            success = true, error = null, token = "", user = UserProfile(
                UUID.randomUUID(),
                userName,
                "$userName@smarthome.com",
                UserGroup(UUID.randomUUID(), "admin")
            )
        )
    }

    override fun logout(token: String) {
    }

}
