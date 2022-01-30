package com.itechart.smg.app.di

import com.arkivanov.decompose.ComponentContext
import com.itechart.smg.core.di.nativeCoreModule
import com.itechart.smg.core.di.sharedCoreModule
import com.itechart.smg.features.auth.di.authModule
import com.itechart.smg.features.auth.domain.AuthComponent
import com.itechart.smg.features.home.di.homeModule
import com.itechart.smg.features.startup.di.startupModule
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

val application = startKoin {
    modules(
        nativeCoreModule,
        sharedCoreModule,
        startupModule,
        authModule,
        homeModule,
        sharedModule
    )
}

object DIRoot {
    inline fun <reified T> get(): T {
        return application.koin.get()
    }

    private inline fun <reified T> getComponentFactory(): (context: ComponentContext) -> T {
        return {
            application.koin.get {
                parametersOf(it)
            }
        }
    }

    fun getAuthComponentFactory(): (context: ComponentContext) -> AuthComponent {
        return getComponentFactory()
    }
}