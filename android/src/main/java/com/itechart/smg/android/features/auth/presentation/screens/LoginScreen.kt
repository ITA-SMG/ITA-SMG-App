package com.itechart.smg.android.features.auth.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.itechart.smg.android.app.utils.Screens
import com.itechart.smg.android.app.utils.reset
import com.itechart.smg.features.auth.domain.AuthComponent

@Composable
fun LoginScreen(navController: NavController, component: AuthComponent) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state = component.state.subscribeAsState()

    LaunchedEffect(state.value.isAuthenticated) {
        if(state.value.isAuthenticated) {
            navController.reset(Screens.Main.name)
        }
    }

    Column {
        Text(text = "Login Screen")
        TextField(
            value = login,
            onValueChange = { login = it },
            placeholder = {
                Text(text = "Login")
            }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(text = "Password")
            }
        )
        if (state.value.isLoading) {
            CircularProgressIndicator()
        }
        Button(
            onClick = {
                component.login(login, password)
            }
        ) {
            Text(text = "Login")
        }
        Text(text = state.toString())
    }
}