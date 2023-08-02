package com.js.smarthome.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.js.smarthome.authentication.ui.screens.AuthenticationScreen
import com.js.smarthome.authentication.ui.screens.AuthenticationViewModel
import com.js.smarthome.home.ui.screens.HomeScreen
import com.js.smarthome.main.navigation.AppNavigation
import com.js.smarthome.main.ui.components.AppScaffold
import com.js.smarthome.main.ui.theme.SmartHomeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: AuthenticationViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SmartHomeTheme {
                val navController = rememberNavController()
                val loginUiState by loginViewModel.uiState.collectAsState()
                val userProfile by loginViewModel.userProfile.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = startDestination(userProfile != null)
                    ) {
                        composable(AppNavigation.Authentication.route) {
                            AuthenticationScreen(
                                loginUiState,
                                loginViewModel::setUserName,
                                loginViewModel::setPassword,
                                loginViewModel::signIn
                            )
                        }

                        composable(AppNavigation.Home.route) {
                            AppScaffold(
                                userProfile = userProfile,
                                logoutAction = loginViewModel::logout,
                                content = { HomeScreen(it) }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun startDestination(isAuthenticated: Boolean): String {
        return if (isAuthenticated)
            AppNavigation.Home.route else
            AppNavigation.Authentication.route
    }
}