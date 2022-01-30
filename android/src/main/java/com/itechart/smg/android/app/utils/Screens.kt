package com.itechart.smg.android.app.utils

sealed class Screens {
    sealed class Splash: Screens() {
        companion object {
            const val name = "SplashScreen"
        }
    }

    sealed class Login: Screens() {
        companion object {
            const val name = "LoginScreen"
        }
    }

    sealed class Main: Screens() {
        companion object {
            const val name = "MainNavigator"
        }
    }
}
