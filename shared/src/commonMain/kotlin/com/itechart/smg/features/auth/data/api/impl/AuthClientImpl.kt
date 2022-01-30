package com.itechart.smg.features.auth.data.api.impl

import by.itechart.smg.types.graphql.generated.LoginMutation
import by.itechart.smg.types.graphql.generated.type.AuthenticationError
import by.itechart.smg.types.graphql.generated.type.RefreshTokenOutput
import com.itechart.smg.core.data.api.APIClient
import com.itechart.smg.core.dto.RefreshTokenOutputDTO
import com.itechart.smg.core.model.AccessTokens
import com.itechart.smg.features.auth.data.api.AuthClient
import com.itechart.smg.features.auth.dto.LoginOutputDTO

class AuthClientImpl(
    private val apiClient: APIClient
): AuthClient {
    override suspend fun login(username: String, password: String): LoginOutputDTO {
        val response = apiClient.mutate(LoginMutation(username, password))
        val data = LoginOutputDTO.fromGraphQLResponse(response)

        apiClient.setAuthorizationHeader(data.data?.token ?: "")

        return data
    }

    override suspend fun logout() {
        apiClient.removeAuthorizationHeader()
    }

    override suspend fun refreshToken(tokens: AccessTokens): RefreshTokenOutputDTO {
        val data = apiClient.updateAuthorizationTokens(tokens)
        apiClient.setAuthorizationHeader(data?.token ?: "")

        return RefreshTokenOutputDTO(
            data = data,
            error = if(data !== null) null else AuthenticationError() //"Tokens unavailable or expired"
        )
    }

}