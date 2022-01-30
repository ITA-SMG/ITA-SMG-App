package com.itechart.smg.core.dto

import by.itechart.smg.types.graphql.generated.RefreshTokenMutation
import by.itechart.smg.types.graphql.generated.type.AuthenticationError
import com.apollographql.apollo3.api.ApolloResponse
import com.itechart.smg.core.model.AccessTokens

data class RefreshTokenOutputDTO(
    val data: AccessTokens? = null,
    val error: AuthenticationError? = null,
) {
    companion object {
        fun fromGraphQLResponse(response: ApolloResponse<RefreshTokenMutation.Data>): RefreshTokenOutputDTO {
            val refreshResponse = response.data?.refreshToken

            val error = if(refreshResponse?.error !== null || refreshResponse === null) {
                AuthenticationError()
            } else null

            val data = if(refreshResponse?.token !== null && refreshResponse.refreshToken !== null) {
                AccessTokens(refreshResponse.token, refreshResponse.refreshToken)
            } else null

            return RefreshTokenOutputDTO(
                data = data,
                error = error
            )
        }
    }
}