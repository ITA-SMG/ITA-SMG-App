package com.itechart.smg.features.auth.data.repository.impl

import com.itechart.smg.core.dto.RefreshTokenOutputDTO
import com.itechart.smg.core.model.AccessTokens
import com.itechart.smg.core.utils.token.AccessTokensProvider
import com.itechart.smg.features.auth.data.api.AuthClient
import com.itechart.smg.features.auth.data.repository.AuthRepository
import com.itechart.smg.features.auth.dto.LoginOutputDTO

class AuthRepositoryImpl(
    private val authClient: AuthClient,
    private val accessTokensProvider: AccessTokensProvider
): AuthRepository {

    override suspend fun getAccessTokensByLoginData(
        username: String,
        password: String
    ): LoginOutputDTO {
        return authClient.login(username, password)
    }

    override suspend fun updateAccessTokens(tokens: AccessTokens): RefreshTokenOutputDTO {
        return authClient.refreshToken(tokens)
    }

    override suspend fun removeAccessTokens() {
        authClient.logout()
        accessTokensProvider.clearTokens()
    }
}