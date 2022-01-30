package com.itechart.smg.app.di

import com.arkivanov.decompose.ComponentContext
import com.itechart.smg.app.domain.RootComponent
import com.itechart.smg.app.domain.impl.RootComponentImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val sharedModule = module {
    factory<RootComponent> { (context: ComponentContext) ->
        RootComponentImpl(context, get {
            parametersOf(context)
        }, get {
            parametersOf(context)
        }, get {
            parametersOf(context)
        })
    }
}