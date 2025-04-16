package com.nikol.presentation.navigation

sealed class MainFeaturesScreens(val route: String) {
    data object MainScreen : MainFeaturesScreens(route = "main_screen")

    companion object {
        const val NAVIGATION_ROUTE = "main_feature_navigation"
        val startScreenRoute = MainScreen.route
    }
}