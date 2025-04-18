package com.nikol.dataauth.remote.repository

import com.nikol.domain.response.RemoteObtainingSignInResult

interface RemoteAuthRepository {
    suspend fun getAccessTokenFromCode(code: String): RemoteObtainingSignInResult
}