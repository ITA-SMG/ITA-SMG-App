package com.itechart.smg.features.home.domain

import com.arkivanov.decompose.value.Value

interface HomeComponent {
    val state: Value<HomeState>
    fun logout()
}