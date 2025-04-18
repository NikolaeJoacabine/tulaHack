package com.nikol.presentation.navigation

import kotlin.math.round

sealed class AuthFeatureScreens(val route: String) {
    data object SignInScreen : AuthFeatureScreens(route = "log_in_screen")
    data object SignUpScreen : AuthFeatureScreens(route = "sign_out_screen")
    data object MainScreen : AuthFeatureScreens(route = "main_feature_navigation")

    companion object {
        const val NAVIGATION_ROUTE = "auth_feature_navigation"
        val startScreenRoute = SignInScreen.route
    }
}