package com.nikol.presentation.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInScreenViewModel @Inject constructor(

) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    var authState = _authState.asStateFlow()

    init {
        tryAutoSignIn()
    }

    private fun tryAutoSignIn() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            delay(1000)
            _authState.value = AuthState.Authenticated
        }
    }
}