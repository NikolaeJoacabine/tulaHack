package com.nikol.domain.response

sealed class RemoteObtainingSignInResult {
    data class Success(
        val accessToken: String,
        val refreshToken: String,
        val expiresIn: Long
    ) : RemoteObtainingSignInResult()

    data class NetworkError(val message: String) : RemoteObtainingSignInResult()
    data class LogInError(val message: String) : RemoteObtainingSignInResult()
    data class Error(val message: String) : RemoteObtainingSignInResult()
}