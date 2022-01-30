package com.itechart.smg.core.utils.token

const val SHARED_PREFERENCES_KEY = "SMG_SHARED_PREFERENCES"

interface DeviceKeystoreProvider {
    fun nativeSet(key: String, value: String)
    fun nativeGet(key: String): String?
}