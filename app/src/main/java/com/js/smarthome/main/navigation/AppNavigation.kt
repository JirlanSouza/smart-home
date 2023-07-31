package com.js.smarthome.main.navigation

sealed class AppNavigation(val route: String) {
    object Authentication : AppNavigation("authentication")
    object Home : AppNavigation("home")
}