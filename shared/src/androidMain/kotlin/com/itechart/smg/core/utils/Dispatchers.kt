package com.itechart.smg.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val networkDispatcher: CoroutineDispatcher = Dispatchers.IO