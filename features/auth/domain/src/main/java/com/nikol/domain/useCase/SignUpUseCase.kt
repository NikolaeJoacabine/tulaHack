package com.nikol.domain.useCase

import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.response.RemoteObtainingSignUpResult

class SignUpUseCase(private val authFeatureRepository: AuthFeatureRepository) {

    suspend fun invoke(login: String, password: String): RemoteObtainingSignUpResult {
        return authFeatureRepository.signUp(login, password)
    }
}