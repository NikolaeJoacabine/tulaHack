package com.nikol.dataauth.local.repository

import com.nikol.dataauth.local.storage.SecureAuthStorage

class LocalAuthRepositoryImpl(
    private val secureAuthStorage: SecureAuthStorage
) : LocalAuthRepository {

    override fun saveTokens(accessToken: String, refreshToken: String, expiresIn: Long, login: String?) {
        secureAuthStorage.saveTokens(accessToken, refreshToken, expiresIn, login)
    }

    override fun getAccessToken(): String? = secureAuthStorage.getAccessToken()

    override fun getRefreshToken(): String? = secureAuthStorage.getRefreshToken()

    override fun getTokenExpiration(): Long = secureAuthStorage.getTokenExpiration()

    override fun isAccessTokenExpired(): Boolean = secureAuthStorage.isAccessTokenExpired()

    override fun getLogin(): String? = secureAuthStorage.getLogin()

    override fun clearTokens() = secureAuthStorage.clearTokens()
}