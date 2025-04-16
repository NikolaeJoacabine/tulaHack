package com.nikol.presentation.di

import com.nikol.navigaiton.FeatureApi
import com.nikol.presentation.navigation.AuthFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class AuthFeatureModule {
    @Provides
    @IntoSet
    fun provideAuthFeatureNavigationApi() : FeatureApi {
        return AuthFeatureImpl()
    }
}