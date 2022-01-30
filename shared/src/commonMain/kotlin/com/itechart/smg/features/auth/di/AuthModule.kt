package com.itechart.smg.features.auth.di

import com.arkivanov.decompose.ComponentContext
import com.itechart.smg.features.auth.data.api.AuthClient
import com.itechart.smg.features.auth.data.api.impl.AuthClientImpl
import com.itechart.smg.features.auth.data.repository.AuthRepository
import com.itechart.smg.features.auth.data.repository.impl.AuthRepositoryImpl
import com.itechart.smg.features.auth.domain.AuthComponent
import com.itechart.smg.features.auth.domain.impl.AuthComponentImpl
import org.koin.dsl.module

val authModule = module {
    single<AuthClient> { AuthClientImpl(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory<AuthComponent> { (context: ComponentContext) ->
        AuthComponentImpl(context, get(), get())
    }
}