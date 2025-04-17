package com.nikol.dataauth.remote.repository

import com.nikol.domain.response.RemoteObtainingSignInResult
import com.nikol.domain.response.RemoteObtainingSignUpResult

interface RemoteAuthRepository {
    suspend fun signIn(login: String, password: String): RemoteObtainingSignInResult
    suspend fun signUp(login: String, password: String): RemoteObtainingSignUpResult
}