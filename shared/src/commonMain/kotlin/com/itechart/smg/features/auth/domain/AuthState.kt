package com.itechart.smg.features.auth.domain

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false
): Parcelable
