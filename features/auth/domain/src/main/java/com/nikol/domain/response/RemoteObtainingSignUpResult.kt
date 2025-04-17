package com.nikol.domain.response

sealed class RemoteObtainingSignUpResult {
    data class Success(val message: String) : RemoteObtainingSignUpResult()
    data class NetworkError(val message: String) : RemoteObtainingSignUpResult()
    data class LogInError(val message: String) : RemoteObtainingSignUpResult()
    data class Error(val message: String) : RemoteObtainingSignUpResult()
}