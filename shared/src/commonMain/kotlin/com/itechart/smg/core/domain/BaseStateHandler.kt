package com.itechart.smg.core.domain

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.itechart.smg.core.utils.networkDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

abstract class BaseStateHandler: InstanceKeeper.Instance {
    val stateHandlerScope = CoroutineScope(networkDispatcher)

    override fun onDestroy() {
        stateHandlerScope.cancel()
    }
}