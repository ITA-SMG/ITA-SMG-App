package com.itechart.smg.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.itechart.smg.android.app.presentation.NavigationProvider
import com.itechart.smg.app.domain.RootComponent
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
    private val decomposeRootComponent: RootComponent by inject { parametersOf(defaultComponentContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationProvider(component = decomposeRootComponent)
        }
    }
}
