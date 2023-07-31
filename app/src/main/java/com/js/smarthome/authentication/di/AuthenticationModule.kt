package com.js.smarthome.authentication.di

import com.js.smarthome.authentication.data.repositories.FakeAuthenticationRepository
import com.js.smarthome.authentication.domain.repositories.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationModule {
    @Binds
    fun bindAuthenticationRepository(
        repository: FakeAuthenticationRepository
    ): AuthenticationRepository
}