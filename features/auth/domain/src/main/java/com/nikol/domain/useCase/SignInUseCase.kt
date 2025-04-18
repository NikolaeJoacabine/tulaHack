package com.nikol.domain.useCase

import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.response.RemoteObtainingSignInResult
import com.nikol.domain.state.AuthState
import com.nikol.domain.state.ErrorType

class SignInUseCase(private val authFeatureRepository: AuthFeatureRepository) {

    suspend fun signInWithCode(authorizationCode: String?): AuthState {

        val currentUser = authFeatureRepository.checkoutCurrentUser()


        return if (currentUser == null) {
            AuthState.Unauthenticated(type = null, message = null)
        } else {
            when (val result = authFeatureRepository.getAccessTokenFromCode(authorizationCode)){
                is RemoteObtainingSignInResult.Success -> AuthState.Authenticated
                is RemoteObtainingSignInResult.LogInError -> AuthState.Unauthenticated(
                    type = ErrorType.LOGIN,
                    message = result.message
                )
                is RemoteObtainingSignInResult.NetworkError -> AuthState.Unauthenticated(
                    type = ErrorType.NETWORK,
                    message = result.message
                )
                is RemoteObtainingSignInResult.Error -> AuthState.Unauthenticated(
                    type = ErrorType.UNKNOWN,
                    message = result.message
                )
            }
        }
    }

    suspend fun manualSignIn(authorizationCode: String): AuthState {
        return when (val result = authFeatureRepository.getAccessTokenFromCode(authorizationCode)){
            is RemoteObtainingSignInResult.Success -> AuthState.Authenticated
            is RemoteObtainingSignInResult.LogInError -> AuthState.Unauthenticated(
                type = ErrorType.LOGIN,
                message = result.message
            )
            is RemoteObtainingSignInResult.NetworkError -> AuthState.Unauthenticated(
                type = ErrorType.NETWORK,
                message = result.message
            )
            is RemoteObtainingSignInResult.Error -> AuthState.Unauthenticated(
                type = ErrorType.UNKNOWN,
                message = result.message
            )
        }
    }
}