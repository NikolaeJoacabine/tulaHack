package com.nikol.dataauth.local.repository

interface LocalAuthRepository {
    fun saveTokens(accessToken: String, expiresIn: Long, authorizationCode: String? = null)  // Добавили параметр для кода
    fun getAccessToken(): String?
    fun getTokenExpiration(): Long
    fun getAuthorizationCode(): String?  // Новый метод для получения кода
    fun isAccessTokenExpired(): Boolean
    fun clearTokens()
}
