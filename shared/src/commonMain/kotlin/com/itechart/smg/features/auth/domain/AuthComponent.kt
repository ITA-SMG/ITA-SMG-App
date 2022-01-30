package com.itechart.smg.features.auth.domain

import com.arkivanov.decompose.value.Value

interface AuthComponent {
    val state: Value<AuthState>
    fun login(username: String, password: String): Unit
}