package com.itechart.smg.core.utils.token

import com.russhwolf.settings.*

@ExperimentalSettingsImplementation
class KeychainProvider: DeviceKeystoreProvider {
    private val keychain: Settings = KeychainSettings(SHARED_PREFERENCES_KEY)

    private fun setValue(key: String, value: String) {
        keychain[key] = value
    }

    private fun getValue(key: String): String? {
        return keychain[key]
    }


    override fun nativeSet(key: String, value: String) {
        setValue(key, value)
    }

    override fun nativeGet(key: String): String? {
        return getValue(key)
    }
}