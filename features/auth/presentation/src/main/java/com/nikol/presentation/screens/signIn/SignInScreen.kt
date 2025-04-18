package com.nikol.presentation.screens.signIn

import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nikol.domain.state.AuthState

@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignInScreenViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsState()

    val authUrl = "https://auth.atlassian.com/authorize" +
            "?audience=api.atlassian.com" +
            "&client_id=m1AvMGC45JfxilxZV1nc0EjlOn7PJuWt" +
            "&scope=read%3Ajira-user%20read%3Ajira-work" +
            "&redirect_uri=myapp://oauth2redirect" +
            "&response_type=code" +
            "&prompt=consent"

    when (authState) {
        is AuthState.Initial,
        is AuthState.Loading -> CircularProgressIndicator()

        is AuthState.Unauthenticated -> {
            val context = LocalContext.current

            // Устанавливаем callback для получения кода
            DisposableEffect(Unit) {
                SignInScreenJira.authCodeCallback = { code ->
                    viewModel.manualSignIn(code)
                }
                onDispose {
                    SignInScreenJira.authCodeCallback = null
                }
            }

            // Запускаем кастомную вкладку один раз
            LaunchedEffect(Unit) {
                val customTabsIntent = CustomTabsIntent.Builder().build()
                customTabsIntent.launchUrl(context, authUrl.toUri())
            }
        }

        is AuthState.Authenticated -> {
                navController.navigate("main_screen") {
                    popUpTo("sign_in") { inclusive = true }
                }
        }
    }
}


object SignInScreenJira {
    var authCodeCallback: ((String) -> Unit)? = null
}