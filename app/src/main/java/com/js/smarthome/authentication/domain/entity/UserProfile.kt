package com.js.smarthome.authentication.domain.entity

import java.util.UUID

data class UserProfile(
    val id: UUID,
    val name: String,
    val email: String,
    val group: UserGroup,
)
