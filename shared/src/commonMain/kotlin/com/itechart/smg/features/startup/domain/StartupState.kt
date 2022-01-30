package com.itechart.smg.features.startup.domain

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class StartupState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = false
): Parcelable
