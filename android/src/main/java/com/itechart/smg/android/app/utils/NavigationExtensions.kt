package com.itechart.smg.android.app.utils

import androidx.navigation.NavController

fun NavController.reset(screen: String) {
    navigate(screen) {
        popUpTo(0)
    }
}