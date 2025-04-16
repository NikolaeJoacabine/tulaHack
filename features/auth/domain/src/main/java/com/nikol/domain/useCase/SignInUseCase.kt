package com.nikol.domain.useCase

import com.nikol.domain.repository.AuthFeatureRepository

class SignInUseCase(private val authFeatureRepository: AuthFeatureRepository) {
    fun invoke() = authFeatureRepository.signIn()
}