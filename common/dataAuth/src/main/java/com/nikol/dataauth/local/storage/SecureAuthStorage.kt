package com.nikol.dataauth.local.storage

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

class SecureAuthStorage(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyGenParameterSpec(
            KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()
        )
        .build()

    private val encryptedPrefs = EncryptedSharedPreferences.create(
        context,
        "secure_tokens",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_EXPIRES_AT = "expires_at"
        private const val KEY_LOGIN = "login"
    }

    fun saveTokens(
        accessToken: String,
        refreshToken: String,
        expiresIn: Long,
        login: String? = null
    ) {
        val expiresAt = System.currentTimeMillis() + expiresIn * 1000
        encryptedPrefs.edit().apply {
            putString(KEY_ACCESS_TOKEN, accessToken)
            putString(KEY_REFRESH_TOKEN, refreshToken)
            putLong(KEY_EXPIRES_AT, expiresAt)
            login?.let { putString(KEY_LOGIN, it) }
            apply()
        }
    }

    fun getAccessToken(): String? = encryptedPrefs.getString(KEY_ACCESS_TOKEN, null)

    fun getRefreshToken(): String? = encryptedPrefs.getString(KEY_REFRESH_TOKEN, null)

    fun getTokenExpiration(): Long = encryptedPrefs.getLong(KEY_EXPIRES_AT, 0)

    fun getLogin(): String? = encryptedPrefs.getString(KEY_LOGIN, null)

    fun isAccessTokenExpired(): Boolean {
        val expiresAt = getTokenExpiration()
        return System.currentTimeMillis() >= expiresAt
    }

    fun clearTokens() {
        encryptedPrefs.edit { clear() }
    }
}


