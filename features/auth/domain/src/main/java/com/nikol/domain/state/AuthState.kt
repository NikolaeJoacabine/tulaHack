package com.nikol.domain.state

sealed class AuthState {
    data object Initial : AuthState()
    data object Loading : AuthState()
    data object Authenticated : AuthState()
    data class Unauthenticated(
        val type: ErrorType? = null,
        val message: String? = null
    ) : AuthState()
}

enum class ErrorType {
    LOGIN,
    NETWORK,
    UNKNOWN
}