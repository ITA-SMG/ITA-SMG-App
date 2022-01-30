package com.itechart.smg.core.di

import com.itechart.smg.core.data.api.APIClient
import com.itechart.smg.core.data.api.impl.APIClientImpl
import com.itechart.smg.core.utils.token.AccessTokensProvider
import org.koin.dsl.module

val sharedCoreModule = module {
    single { AccessTokensProvider(get()) }
    single<APIClient> { APIClientImpl(get()) }
}