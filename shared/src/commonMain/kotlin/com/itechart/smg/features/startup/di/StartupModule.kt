package com.itechart.smg.features.startup.di

import com.arkivanov.decompose.ComponentContext
import com.itechart.smg.features.startup.domain.StartupComponent
import com.itechart.smg.features.startup.domain.impl.StartupComponentImpl
import org.koin.dsl.module

val startupModule = module {
    factory<StartupComponent> { (context: ComponentContext) ->
        StartupComponentImpl(context, get(), get())
    }
}