package com.nikol.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikol.navigaiton.FeatureApi
import com.nikol.presentation.screens.signIn.SignInScreen

class AuthFeatureImpl : FeatureApi {
    override val navigationRoute = AuthFeatureScreens.NAVIGATION_ROUTE
    override val startDestination = AuthFeatureScreens.startScreenRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = startDestination,
            route = navigationRoute
        ) {
            composable(
                route = AuthFeatureScreens.SignInScreen.route
            ) {
                SignInScreen(navController, modifier)
            }
            composable(
                route = AuthFeatureScreens.SignUpScreen.route
            ) {

            }
        }
    }
}