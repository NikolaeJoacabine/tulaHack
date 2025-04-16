package com.nikol.tulahack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikol.navigaiton.BottomBarItem
import com.nikol.navigaiton.FeatureApi
import com.nikol.tulahack.navigation.AppNavGraph

@Composable
fun AppContent(
    bottomBarItems: List<BottomBarItem>,
    featureNavigationApis: List<FeatureApi>
) {
    val navController = rememberNavController()
    val currentDestinationRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val currentDestinationParentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.parent?.route

    val visible =
        featureNavigationApis.any { it.startDestination == currentDestinationRoute } || currentDestinationParentRoute == null

    val showBottomBar = bottomBarItems.any { item ->
        item.navigationRoute == currentDestinationRoute || item.navigationRoute == currentDestinationParentRoute
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                BottomBar(
                    navController = navController,
                    currentDestinationParentRoute = currentDestinationParentRoute,
                    items = bottomBarItems
                )
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navController = navController,
            featureNavigationApis = featureNavigationApis,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    currentDestinationParentRoute: String?,
    items: List<BottomBarItem>
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = Color(0xFF4E5054).copy(0.5f),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
            }
            .padding(top = 1.dp)
    ) {
        items.forEach { bottomBarItem ->
            NavigationBarItem(
                selected = currentDestinationParentRoute == bottomBarItem.navigationRoute,
                onClick = {
                    navController.navigate(bottomBarItem.navigationRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(bottomBarItem.iconId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottomBarItem.nameId),
                    )
                },
            )
        }
    }
}