package com.itechart.smg.features.home.domain

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class HomeState(
    val isLoading: Boolean = false
): Parcelable
