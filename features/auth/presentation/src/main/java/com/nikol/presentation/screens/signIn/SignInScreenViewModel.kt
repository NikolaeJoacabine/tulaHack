package com.nikol.presentation.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.state.AuthState
import com.nikol.domain.useCase.SignInUseCase
import com.nikol.domain.useCase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    var authState = _authState.asStateFlow()

    init {
        tryAutoSignIn()
    }

    private fun tryAutoSignIn() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            _authState.value = signInUseCase.tryAutoSignIn()
        }
    }

    fun manualSignIn(login: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            _authState.value = signInUseCase.manualSignIn(login, password)
        }
    }
}