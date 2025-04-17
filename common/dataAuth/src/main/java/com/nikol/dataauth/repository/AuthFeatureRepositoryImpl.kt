package com.nikol.dataauth.repository

import com.nikol.dataauth.local.repository.LocalAuthRepository
import com.nikol.dataauth.remote.repository.RemoteAuthRepository
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.response.RemoteObtainingSignInResult
import com.nikol.domain.response.RemoteObtainingSignUpResult

class AuthFeatureRepositoryImpl(
    private val remoteAuthRepository: RemoteAuthRepository,
    private val localAuthRepository: LocalAuthRepository
) : AuthFeatureRepository {

    override suspend fun signIn(login: String, password: String): RemoteObtainingSignInResult {

        return remoteAuthRepository.signIn(login, password).also { result ->
            if (result is RemoteObtainingSignInResult.Success) {
                localAuthRepository.saveTokens(
                    accessToken = result.token,
                    refreshToken = result.refreshToken,
                    expiresIn = result.expired,
                    login = login
                )
            }
        }
    }

    //доделать
    override suspend fun autoSignIn(): RemoteObtainingSignInResult {
        val refreshToken = localAuthRepository.getRefreshToken()
        val login = localAuthRepository.getLogin()

        if (refreshToken.isNullOrBlank() || login.isNullOrBlank()) {
            return RemoteObtainingSignInResult.LogInError("No refresh token or login")
        }

        return if (localAuthRepository.isAccessTokenExpired()) {

            //ходим за токеном через рефреш и обновляем
            //дальше сохраняем и

            remoteAuthRepository.signIn(login, refreshToken).also { result ->
                if (result is RemoteObtainingSignInResult.Success) {
                    localAuthRepository.saveTokens(
                        accessToken = result.token,
                        refreshToken = result.refreshToken,
                        expiresIn = 3600
                    )
                } else {
                    localAuthRepository.clearTokens()
                }
            }
        } else {
            RemoteObtainingSignInResult.Success()
        }
    }

    override suspend fun signUp(login: String, password: String): RemoteObtainingSignUpResult {

        return remoteAuthRepository.signUp(login, password).also { result ->
            if (result is RemoteObtainingSignUpResult.Success) {
                signIn(login, password)
            }
        }
    }

    override suspend fun checkoutCurrentUser(): String? {
        return localAuthRepository.getLogin()
    }

    override suspend fun logout() {
        localAuthRepository.clearTokens()
    }
}