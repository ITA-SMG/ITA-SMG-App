package com.itechart.smg.features.auth.dto

import by.itechart.smg.types.graphql.generated.LoginMutation
import by.itechart.smg.types.graphql.generated.type.AuthenticationError
import com.apollographql.apollo3.api.ApolloResponse
import com.itechart.smg.core.model.AccessTokens

data class LoginOutputDTO(
    val data: AccessTokens? = null,
    val error: AuthenticationError? = null,
) {
    companion object {
        fun fromGraphQLResponse(response: ApolloResponse<LoginMutation.Data>): LoginOutputDTO {
            val loginResponse = response.data?.login

            val error = if(loginResponse?.error !== null || loginResponse === null) {
                AuthenticationError()
            } else null

            val data = if(loginResponse?.token !== null && loginResponse.refreshToken !== null) {
                AccessTokens(loginResponse.token, loginResponse.refreshToken)
            } else null

            return LoginOutputDTO(
                data = data,
                error = error
            )
        }
    }
}
