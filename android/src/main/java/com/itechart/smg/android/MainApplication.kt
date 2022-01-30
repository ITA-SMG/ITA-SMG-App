package com.itechart.smg.android

import android.app.Application
import com.itechart.smg.app.di.sharedModule
import com.itechart.smg.core.di.nativeCoreModule
import com.itechart.smg.core.di.sharedCoreModule
import com.itechart.smg.features.auth.di.authModule
import com.itechart.smg.features.home.di.homeModule
import com.itechart.smg.features.startup.di.startupModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)

            modules(
                nativeCoreModule,
                sharedCoreModule,
                startupModule,
                authModule,
                homeModule,
                sharedModule
            )
        }
    }
}