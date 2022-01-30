package com.itechart.smg.core.data.api.impl

import by.itechart.smg.types.graphql.generated.RefreshTokenMutation
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Mutation
import com.apollographql.apollo3.api.Query
import com.itechart.smg.core.data.api.APIClient
import com.itechart.smg.core.dto.RefreshTokenOutputDTO
import com.itechart.smg.core.model.AccessTokens
import com.itechart.smg.core.utils.AuthorizationInterceptor
import com.itechart.smg.core.utils.errorMessage
import com.itechart.smg.core.utils.token.AccessTokensProvider
import com.apollographql.apollo3.api.Error as ApolloError

class APIClientImpl(
    private val accessTokensProvider: AccessTokensProvider,
): APIClient {
    private var apolloInstance: ApolloClient = getApolloInstance()
    private lateinit var authStateChangedListener: (isAuthenticated: Boolean) -> Unit

    private companion object {
        const val SERVER_ADDRESS = "https://smg-graphql-proxy.herokuapp.com/graphql"
    }

    private fun getApolloInstance(token: String? = null): ApolloClient {
        return ApolloClient
                .Builder()
                .serverUrl(SERVER_ADDRESS)
                .interceptors(listOf(AuthorizationInterceptor(token)))
                .build()
    }

    private suspend fun tryUpdateAuthorizationTokens(): Boolean {
        val persistedTokens = accessTokensProvider.getAccessTokens() ?: return false

        val tokens = updateAuthorizationTokens(persistedTokens)

        tokens?.let {
            accessTokensProvider.setAccessTokens(tokens)

            authStateChangedListener(true);

            setAuthorizationHeader(tokens.token)

            return true
        }

        authStateChangedListener(false)

        return false
    }

    override suspend fun <D : Query.Data>
            query(query: Query<D>): ApolloResponse<D> {

        val response = apolloInstance.query(query).execute()

        response.data?.let {
            return response
        }

        response.errors?.let {
            val message = response.errorMessage

            return if(message === "Unauthorized") {
                tryUpdateAuthorizationTokens()

                apolloInstance.query(query).execute()
            } else {
                response
            }
        }

        return response
            .newBuilder()
            .errors(listOf(ApolloError("Unknown error", null, null, null, null)))
            .build()
    }

    override suspend fun <D : Mutation.Data>
            mutate(mutation: Mutation<D>): ApolloResponse<D> {

        val response = apolloInstance.mutate(mutation).execute()

        response.data?.let {
            return response
        }

        response.errors?.let {
            val message = response.errorMessage

            return if(message === "Unauthorized") {
                tryUpdateAuthorizationTokens()

                apolloInstance.mutate(mutation).execute()
            } else {
                response
            }
        }

        return response
            .newBuilder()
            .errors(listOf(ApolloError("Unknown error", null, null, null, null)))
            .build()
    }

    override fun setAuthorizationHeader(token: String) {
        apolloInstance = getApolloInstance(token)
    }

    override fun removeAuthorizationHeader() {
        apolloInstance = getApolloInstance()
    }

    override fun setAuthStateChangedListener(listener: (isAuthenticated: Boolean) -> Unit) {
        this.authStateChangedListener = listener
    }

    override suspend fun updateAuthorizationTokens(tokens: AccessTokens): AccessTokens? {
        val result = mutate(RefreshTokenMutation(tokens.refreshToken))
        val data = RefreshTokenOutputDTO.fromGraphQLResponse(result)

        data.data?.let {
            return it
        }

        return null
    }
}