package com.itechart.smg.features.home.domain.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.statekeeper.consume
import com.itechart.smg.core.domain.BaseStateHandler
import com.itechart.smg.core.utils.token.AccessTokensProvider
import com.itechart.smg.features.auth.data.repository.AuthRepository
import com.itechart.smg.features.home.data.HomeRepository
import com.itechart.smg.features.home.domain.HomeComponent
import com.itechart.smg.features.home.domain.HomeState
import kotlinx.coroutines.launch

class HomeComponentImpl(
    private val context: ComponentContext,
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
): HomeComponent, ComponentContext by context {

    private companion object {
        private const val MEMOIZATION_KEY = "HOME_COMPONENT"
    }

    private val handler = instanceKeeper.getOrCreate(MEMOIZATION_KEY) {
        HomeStateHandler(stateKeeper.consume(MEMOIZATION_KEY) ?: HomeState(), authRepository)
    }

    override val state: Value<HomeState> = handler.state

    init {
        stateKeeper.register(MEMOIZATION_KEY) {
            handler.state.value
        }
    }

    override fun logout() {
        handler.logout()
    }

    private class HomeStateHandler(
        initialState: HomeState,
        private val authRepository: AuthRepository
    ): BaseStateHandler() {
        val state: MutableValue<HomeState> = MutableValue(initialState)

        fun logout() {
            stateHandlerScope.launch {
                authRepository.removeAccessTokens()
            }
        }
    }
}