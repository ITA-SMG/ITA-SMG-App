package com.itechart.smg.features.home.di

import com.arkivanov.decompose.ComponentContext
import com.itechart.smg.features.home.data.HomeRepository
import com.itechart.smg.features.home.data.impl.HomeRepositoryImpl
import com.itechart.smg.features.home.domain.HomeComponent
import com.itechart.smg.features.home.domain.impl.HomeComponentImpl
import org.koin.dsl.module

val homeModule = module {
    single<HomeRepository> { HomeRepositoryImpl() }

    factory<HomeComponent> { (context: ComponentContext) ->
        HomeComponentImpl(context, get(), get())
    }
}