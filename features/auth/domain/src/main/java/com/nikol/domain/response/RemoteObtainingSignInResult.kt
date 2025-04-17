package com.nikol.domain.response

sealed class RemoteObtainingSignInResult {
    data class Success(
        val token: String = "",
        val refreshToken: String = "",
        val expired: Long = 0L
    ) : RemoteObtainingSignInResult()

    data class NetworkError(val message: String) : RemoteObtainingSignInResult()
    data class LogInError(val message: String) : RemoteObtainingSignInResult()
    data class Error(val message: String) : RemoteObtainingSignInResult()
}