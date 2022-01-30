package com.itechart.smg.core.di

import com.itechart.smg.core.utils.token.DeviceKeystoreProvider
import com.itechart.smg.core.utils.token.EncryptedSharedPreferencesProvider
import org.koin.dsl.module

actual val nativeCoreModule = module {
    single<DeviceKeystoreProvider> { EncryptedSharedPreferencesProvider(get()) }
}