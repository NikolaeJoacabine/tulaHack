package com.nikol.tulahack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikol.navigaiton.BottomBarItem
import com.nikol.navigaiton.FeatureApi
import com.nikol.tulahack.ui.theme.TulaHackTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bottomBarItems: List<@JvmSuppressWildcards BottomBarItem>

    @Inject
    lateinit var featureNavigationApis: List<@JvmSuppressWildcards FeatureApi>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val list = listOf("auth_feature_navigation", "main_feature_navigation")
        setContent {
            TulaHackTheme {
                AppContent(
                    bottomBarItems = bottomBarItems.toList()
                        .sortedBy { list.indexOf(it.navigationRoute) },
                    featureNavigationApis = featureNavigationApis.toList()
                        .sortedBy { list.indexOf(it.navigationRoute) }
                )
            }
        }
    }
}