package com.itechart.smg.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_queue_create
import kotlin.coroutines.CoroutineContext

object NetworkDispatcher: CoroutineDispatcher() {
    private val customQueue = dispatch_queue_create("NetworkDispatcher", null)

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(customQueue) {
            block.run()
        }
    }
}

actual val networkDispatcher: CoroutineDispatcher = Dispatchers.Main