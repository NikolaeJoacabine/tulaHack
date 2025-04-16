package com.nikol.presentation.screens.signIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignInScreenViewModel = hiltViewModel()
) {
    Column {


        Text("вход")
        Spacer(Modifier.size(40.dp))
        Button(onClick = { navController.navigate("main_feature_navigation") }) {
            Text("fgf")
        }
    }
}