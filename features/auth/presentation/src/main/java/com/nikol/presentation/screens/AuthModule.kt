package com.nikol.presentation.screens

import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.useCase.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {

    @Provides
    fun provideSignInUseCase(authFeatureRepository: AuthFeatureRepository): SignInUseCase {
        return SignInUseCase(authFeatureRepository)
    }

}