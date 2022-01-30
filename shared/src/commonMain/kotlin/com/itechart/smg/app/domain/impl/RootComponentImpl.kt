package com.itechart.smg.app.domain.impl

import com.arkivanov.decompose.ComponentContext
import com.itechart.smg.app.domain.RootComponent
import com.itechart.smg.features.auth.domain.AuthComponent
import com.itechart.smg.features.home.domain.HomeComponent
import com.itechart.smg.features.startup.domain.StartupComponent

class RootComponentImpl(
    private val context: ComponentContext,
    override val startupComponent: StartupComponent,
    override val authComponent: AuthComponent,
    override val homeComponent: HomeComponent
): RootComponent {
}