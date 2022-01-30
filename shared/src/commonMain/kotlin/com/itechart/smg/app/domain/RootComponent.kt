package com.itechart.smg.app.domain

import com.itechart.smg.features.auth.domain.AuthComponent
import com.itechart.smg.features.home.domain.HomeComponent
import com.itechart.smg.features.startup.domain.StartupComponent

interface RootComponent {
    val startupComponent: StartupComponent
    val authComponent: AuthComponent
    val homeComponent: HomeComponent
}