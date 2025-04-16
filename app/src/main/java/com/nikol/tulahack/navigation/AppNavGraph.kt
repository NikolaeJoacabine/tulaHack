package com.nikol.tulahack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nikol.navigaiton.FeatureApi
import com.nikol.navigaiton.register

@Composable
fun AppNavGraph(
    navController: NavHostController,
    featureNavigationApis: List<FeatureApi>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = featureNavigationApis.first().navigationRoute,
    ) {
        featureNavigationApis.forEach { featureNavigationApi ->
            register(
                featureApi = featureNavigationApi,
                navController = navController,
                modifier = modifier
            )
        }
    }
}