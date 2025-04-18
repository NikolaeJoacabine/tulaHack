package com.nikol.domain.repository

import com.nikol.domain.response.RemoteObtainingSignInResult

interface AuthFeatureRepository {
    suspend fun getAccessTokenFromCode(authorizationCode: String?): RemoteObtainingSignInResult
    suspend fun checkoutCurrentUser(): String?
    suspend fun logout()
}