package com.js.smarthome.authentication.ui.screens

import androidx.lifecycle.ViewModel
import com.js.smarthome.authentication.domain.entity.UserProfile
import com.js.smarthome.authentication.domain.error.AuthenticationError
import com.js.smarthome.authentication.domain.repositories.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthenticationUiState())
    private val _userProfile: MutableStateFlow<UserProfile?> = MutableStateFlow(null)
    val uiState: StateFlow<AuthenticationUiState> = _uiState.asStateFlow()
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    fun setUserName(userName: String) {
        _uiState.update { state -> state.copy(userName = userName) }
        validateUserName()
    }

    fun setPassword(password: String) {
        _uiState.update { state -> state.copy(password = password) }
        validatePassword()
    }

    fun login() {
        toggleLoading()

        validateUserName()
        validatePassword()

        if (_uiState.value.userNameError || _uiState.value.passwordError) {
            toggleLoading()
            return
        }

        val result = repository.authenticate(_uiState.value.userName, _uiState.value.password)

        if (result.success) {
            _userProfile.value = result.user
            toggleLoading()
            return
        }

        handleAuthenticationFailure(result.error)
    }

    fun logout() {
        toggleLoading()
        _userProfile.value = null
        _uiState.update { cs -> cs.copy(isLoading = false) }
    }

    fun resetAuthenticationError() {
        _uiState.update { cs -> cs.copy(error = false) }
    }

    private fun validateUserName(): Boolean {
        val isValid = _uiState.value.userName.length >= 3
        _uiState.update { cs -> cs.copy(userNameError = !isValid) }
        return isValid
    }

    private fun validatePassword(): Boolean {
        val isValid = _uiState.value.password.length >= 6
        _uiState.update { cs -> cs.copy(passwordError = !isValid) }
        return isValid
    }

    private fun toggleLoading() {
        _uiState.update { cs -> cs.copy(isLoading = !cs.isLoading) }
    }

    private fun handleAuthenticationFailure(error: AuthenticationError?) {
        _uiState.update { cs ->
            cs.copy(
                isLoading = !cs.isLoading,
                error = true
            )
        }
    }
}