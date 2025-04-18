package com.nikol.dataauth.remote.network

import com.nikol.dataauth.remote.models.SignInResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth/token")
    suspend fun getAccessTokenFromCode(
        @Header("Authorization") code: String
    ) : SignInResponse
}