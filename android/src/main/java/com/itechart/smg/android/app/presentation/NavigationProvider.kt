package com.itechart.smg.android.app.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itechart.smg.android.app.utils.Screens
import com.itechart.smg.android.app.utils.reset
import com.itechart.smg.android.features.auth.presentation.screens.LoginScreen
import com.itechart.smg.android.features.home.presentation.screens.HomeScreen
import com.itechart.smg.android.features.startup.presentation.screens.SplashScreen
import com.itechart.smg.app.domain.RootComponent
import com.itechart.smg.core.data.api.APIClient
import org.koin.androidx.compose.get

@Composable
fun NavigationProvider(component: RootComponent, apiClient: APIClient = get()) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        apiClient.setAuthStateChangedListener {
            print("Auth state changed to $it")

            /*if(!it) {
                navController.reset(Screens.Login.name)
            }*/
        }
    }

    NavHost(navController = navController, startDestination = Screens.Splash.name) {
        composable(Screens.Splash.name) {
            SplashScreen(navController = navController, component = component.startupComponent)
        }

        composable(Screens.Login.name) {
            LoginScreen(navController = navController, component = component.authComponent)
        }

        composable(Screens.Main.name) {
            HomeScreen(navController = navController, component = component.homeComponent)
        }
    }
}