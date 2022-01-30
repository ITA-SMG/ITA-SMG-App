package com.itechart.smg.android.features.startup.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.itechart.smg.android.app.utils.Screens
import com.itechart.smg.android.app.utils.reset
import com.itechart.smg.features.startup.domain.StartupComponent

@Composable
fun SplashScreen(navController: NavController, component: StartupComponent) {
    val state = component.state.subscribeAsState()

    LaunchedEffect(state.value.isLoading) {
        if(!state.value.isLoading && state.value.isAuthenticated) {
            navController.reset(Screens.Main.name)
        } else if(!state.value.isLoading && !state.value.isAuthenticated) {
            navController.reset(Screens.Login.name)
        }
    }

    LaunchedEffect(Unit) {
        component.validatePersistedUserCredentials()
    }

    Column {
        Text(text = state.value.toString())
        Text(text = "Splash Screen")
        if(state.value.isLoading) {
            CircularProgressIndicator()
        }
    }
}