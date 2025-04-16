package com.nikol.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikol.navigaiton.FeatureApi

class MainFeatureImpl : FeatureApi {
    override val navigationRoute = MainFeaturesScreens.NAVIGATION_ROUTE
    override val startDestination = MainFeaturesScreens.startScreenRoute

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
                route = MainFeaturesScreens.MainScreen.route
            ) {  }
        }
    }
}