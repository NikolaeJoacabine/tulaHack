package com.nikol.dataauth.di

import android.content.Context
import com.nikol.dataauth.local.repository.LocalAuthRepository
import com.nikol.dataauth.local.repository.LocalAuthRepositoryImpl
import com.nikol.dataauth.local.storage.SecureAuthStorage
import com.nikol.dataauth.remote.network.AuthApi
import com.nikol.dataauth.remote.repository.RemoteAuthRepository
import com.nikol.dataauth.remote.repository.RemoteAuthRepositoryImpl
import com.nikol.dataauth.repository.AuthFeatureRepositoryImpl
import com.nikol.domain.repository.AuthFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthFeatureDataModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSecureAuthStorage(@ApplicationContext context: Context): SecureAuthStorage {
        return SecureAuthStorage(context)
    }

    @Provides
    @Singleton
    fun provideLocalAuthFeatureRepository(secureAuthStorage: SecureAuthStorage): LocalAuthRepository {
        return LocalAuthRepositoryImpl(secureAuthStorage)
    }

    @Provides
    @Singleton
    fun provideRemoteAuthFeatureRepository(authApi: AuthApi): RemoteAuthRepository {
        return RemoteAuthRepositoryImpl(authApi)
    }


    @Provides
    @Singleton
    fun provideAuthFeatureRepository(
        remoteAuthFeatureRepository: RemoteAuthRepository,
        localAuthFeaturesRepository: LocalAuthRepository,
    ): AuthFeatureRepository {
        return AuthFeatureRepositoryImpl(
            remoteAuthFeatureRepository,
            localAuthFeaturesRepository
        )
    }
}