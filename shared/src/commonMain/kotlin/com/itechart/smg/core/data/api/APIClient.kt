package com.itechart.smg.core.data.api

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Mutation
import com.apollographql.apollo3.api.Query
import com.itechart.smg.core.model.AccessTokens

interface APIClient {
    suspend fun <D : Query.Data> query(query: Query<D>): ApolloResponse<D>
    suspend fun <D : Mutation.Data> mutate(mutation: Mutation<D>): ApolloResponse<D>
    fun setAuthorizationHeader(token: String)
    fun removeAuthorizationHeader()
    fun setAuthStateChangedListener(listener: (isAuthenticated: Boolean) -> Unit)
    suspend fun updateAuthorizationTokens(tokens: AccessTokens): AccessTokens?
}