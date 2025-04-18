package com.nikol.dataauth.remote.repository

import com.nikol.dataauth.remote.network.AuthApi
import com.nikol.domain.response.RemoteObtainingSignInResult

class RemoteAuthRepositoryImpl(
    private val authApi: AuthApi
) : RemoteAuthRepository {

    override suspend fun getAccessTokenFromCode(code: String): RemoteObtainingSignInResult {
        try {
            val response = authApi.getAccessTokenFromCode(code)
            return RemoteObtainingSignInResult.Success(response.accessToken, response.expiresIn)
        } catch (e: Exception) {
            return RemoteObtainingSignInResult.Error("error")
        }
    }
}