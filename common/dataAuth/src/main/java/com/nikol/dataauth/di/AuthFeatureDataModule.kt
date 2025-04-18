package com.nikol.dataauth.di

import com.nikol.dataauth.repository.AuthFeatureRepositoryImpl
import com.nikol.domain.repository.AuthFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthFeatureDataModule {


}