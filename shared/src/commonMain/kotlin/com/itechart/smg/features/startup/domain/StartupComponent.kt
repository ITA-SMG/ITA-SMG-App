package com.itechart.smg.features.startup.domain

import com.arkivanov.decompose.value.Value

interface StartupComponent {
    val state: Value<StartupState>
    fun validatePersistedUserCredentials()
}