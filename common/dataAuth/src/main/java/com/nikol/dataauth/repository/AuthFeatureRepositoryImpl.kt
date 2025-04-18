package com.nikol.dataauth.repository

import com.nikol.dataauth.local.repository.LocalAuthRepository
import com.nikol.dataauth.remote.repository.RemoteAuthRepository
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.response.RemoteObtainingSignInResult

class AuthFeatureRepositoryImpl(
    private val remoteAuthRepository: RemoteAuthRepository,
    private val localAuthRepository: LocalAuthRepository
) : AuthFeatureRepository {


    override suspend fun getAccessTokenFromCode(authorizationCode: String?): RemoteObtainingSignInResult {
        val code = authorizationCode ?: localAuthRepository.getAuthorizationCode()
        return remoteAuthRepository.getAccessTokenFromCode(code ?: "").also { result ->
            if (result is RemoteObtainingSignInResult.Success) {
                localAuthRepository.saveTokens(
                    accessToken = result.token,
                    expiresIn = result.expired,
                    authorizationCode = authorizationCode
                )
            }
        }
    }

    override suspend fun checkoutCurrentUser(): String? {
        return localAuthRepository.getAccessToken()
    }

    override suspend fun logout() {
        localAuthRepository.clearTokens()
    }
}