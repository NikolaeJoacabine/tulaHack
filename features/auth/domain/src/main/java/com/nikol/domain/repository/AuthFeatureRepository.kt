package com.nikol.domain.repository

import com.nikol.domain.response.RemoteObtainingSignInResult
import com.nikol.domain.response.RemoteObtainingSignUpResult

interface AuthFeatureRepository {
    suspend fun signIn(login: String, password: String): RemoteObtainingSignInResult
    suspend fun autoSignIn(): RemoteObtainingSignInResult
    suspend fun signUp(login: String, password: String): RemoteObtainingSignUpResult
    suspend fun checkoutCurrentUser(): String?
    suspend fun logout()
}