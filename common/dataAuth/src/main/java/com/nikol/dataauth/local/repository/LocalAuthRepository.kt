package com.nikol.dataauth.local.repository

interface LocalAuthRepository {
    fun saveTokens(accessToken: String, refreshToken: String, expiresIn: Long, login: String? = null)
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun getTokenExpiration(): Long
    fun isAccessTokenExpired(): Boolean
    fun getLogin(): String?
    fun clearTokens()
}
