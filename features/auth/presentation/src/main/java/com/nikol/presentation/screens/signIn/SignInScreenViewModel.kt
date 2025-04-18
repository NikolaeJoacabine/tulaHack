package com.nikol.presentation.screens.signIn

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.state.AuthState
import com.nikol.domain.useCase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    var authState = _authState.asStateFlow()

    init {
        tryAutoSignIn()
    }

    private fun tryAutoSignIn() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            signInUseCase.signInWithCode(null).let {
                _authState.value = it
            }
        }
    }

    fun manualSignIn(code: String) {
        Log.d("info", code)
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            signInUseCase.signInWithCode(code).let {
                _authState.value = it
            }
        }
    }
}