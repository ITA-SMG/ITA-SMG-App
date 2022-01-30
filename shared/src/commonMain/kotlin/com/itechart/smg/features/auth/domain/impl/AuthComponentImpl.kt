package com.itechart.smg.features.auth.domain.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.itechart.smg.features.auth.data.repository.AuthRepository
import com.itechart.smg.features.auth.domain.AuthState
import com.arkivanov.essenty.statekeeper.consume
import com.itechart.smg.core.domain.BaseStateHandler
import com.itechart.smg.core.utils.token.AccessTokensProvider
import com.itechart.smg.features.auth.domain.AuthComponent
import kotlinx.coroutines.launch

class AuthComponentImpl(
    private val context: ComponentContext,
    private val authRepository: AuthRepository,
    private val accessTokensProvider: AccessTokensProvider
): AuthComponent, ComponentContext by context {

    private companion object {
        private const val MEMOIZATION_KEY = "AUTH_COMPONENT"
    }

    private val handler = instanceKeeper.getOrCreate(MEMOIZATION_KEY) {
        AuthStateHandler(
            stateKeeper.consume(MEMOIZATION_KEY) ?: AuthState(),
            authRepository,
            accessTokensProvider
        )
    }

    override val state: Value<AuthState> = handler.state

    init {
        stateKeeper.register(MEMOIZATION_KEY) {
            handler.state.value
        }
    }

    override fun login(username: String, password: String) {
        handler.login(username, password)
    }

    private class AuthStateHandler(
        initialState: AuthState,
        private val authRepository: AuthRepository,
        private val accessTokensProvider: AccessTokensProvider
    ): BaseStateHandler() {
        val state: MutableValue<AuthState> = MutableValue(initialState)

        fun login(username: String, password: String) {
            stateHandlerScope.launch {
                state.value = state.value.copy(isLoading = true)

                val data = authRepository.getAccessTokensByLoginData(username, password)

                if(data.data == null) {
                    state.value = state.value.copy(isLoading = false, isAuthenticated = false)

                    return@launch
                }

                accessTokensProvider.setAccessTokens(data.data)
                state.value = state.value.copy(isLoading = false, isAuthenticated = true)
            }
        }
    }
}