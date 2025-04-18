package com.nikol.dataauth.local.repository

import com.nikol.dataauth.local.storage.SecureAuthStorage

class LocalAuthRepositoryImpl(
    private val secureAuthStorage: SecureAuthStorage
) : LocalAuthRepository {

    override fun saveTokens(accessToken: String, expiresIn: Long, authorizationCode: String?) {
        secureAuthStorage.saveTokens(accessToken, expiresIn, authorizationCode)
    }

    override fun getAccessToken(): String? = secureAuthStorage.getAccessToken()

    override fun getTokenExpiration(): Long = secureAuthStorage.getTokenExpiration()

    override fun getAuthorizationCode(): String? = secureAuthStorage.getAuthorizationCode()  // Новый метод

    override fun isAccessTokenExpired(): Boolean = secureAuthStorage.isAccessTokenExpired()

    override fun clearTokens() = secureAuthStorage.clearTokens()
}
