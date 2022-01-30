package com.itechart.smg.features.startup.domain.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.statekeeper.consume
import com.itechart.smg.core.domain.BaseStateHandler
import com.itechart.smg.core.utils.token.AccessTokensProvider
import com.itechart.smg.features.auth.data.repository.AuthRepository
import com.itechart.smg.features.startup.domain.StartupComponent
import com.itechart.smg.features.startup.domain.StartupState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartupComponentImpl(
    private val context: ComponentContext,
    private val accessTokensProvider: AccessTokensProvider,
    private val authRepository: AuthRepository,
): StartupComponent, ComponentContext by context {

    private companion object {
        private const val MEMOIZATION_KEY = "STARTUP_COMPONENT"
    }

    private val handler = instanceKeeper.getOrCreate(MEMOIZATION_KEY) {
        StartupStateHandler(
            stateKeeper.consume(MEMOIZATION_KEY) ?: StartupState(),
            accessTokensProvider,
            authRepository
        )
    }

    override val state: Value<StartupState> = handler.state

    init {
        stateKeeper.register(MEMOIZATION_KEY) {
            handler.state.value
        }
    }

    override fun validatePersistedUserCredentials() {
        handler.validatePersistedUserCredentials()
    }

    private class StartupStateHandler(
        initialState: StartupState,
        private val accessTokensProvider: AccessTokensProvider,
        private val authRepository: AuthRepository
    ): BaseStateHandler() {
        val state: MutableValue<StartupState> = MutableValue(initialState)

        fun validatePersistedUserCredentials() {
            stateHandlerScope.launch {
                val tokens = accessTokensProvider.getAccessTokens()

                print(tokens)

                if(tokens == null || tokens.token.isEmpty()) {
                    state.value = state.value.copy(isLoading = false, isAuthenticated = false)

                    return@launch
                }

                val refreshed = authRepository.updateAccessTokens(tokens)

                if(refreshed.data == null) {
                    state.value = state.value.copy(isLoading = false, isAuthenticated = false)

                    return@launch
                }

                accessTokensProvider.setAccessTokens(refreshed.data)
                state.value = state.value.copy(isLoading = false, isAuthenticated = true)
            }
        }
    }
}