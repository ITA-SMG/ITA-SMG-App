package com.itechart.smg.android.features.home.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.itechart.smg.android.app.utils.Screens
import com.itechart.smg.android.app.utils.reset
import com.itechart.smg.features.home.domain.HomeComponent

@Composable
fun HomeScreen(navController: NavController, component: HomeComponent) {
    val state = component.state.subscribeAsState()

    val logout = {
        component.logout()
        navController.reset(Screens.Login.name)
    }

    Column {
        Text(text = state.value.toString())
        Button(onClick = logout) {
            Text(text = "Logout")
        }
    }
}