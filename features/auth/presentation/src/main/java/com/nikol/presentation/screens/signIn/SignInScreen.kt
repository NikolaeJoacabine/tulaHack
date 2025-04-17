package com.nikol.presentation.screens.signIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nikol.domain.state.AuthState

@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignInScreenViewModel = hiltViewModel()
) {
    Column {

        val authState by viewModel.authState.collectAsState()

        Text("вход")
        Spacer(Modifier.size(40.dp))
        when(val state = authState){
            is AuthState.Unauthenticated ->{
                Text("${state.type}")
            }
            else -> {

            }
        }
    }
}