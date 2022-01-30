package com.itechart.smg.core.utils.token

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncryptedSharedPreferencesProvider(
    private val context: Context
): DeviceKeystoreProvider {
    private var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        SHARED_PREFERENCES_KEY,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private fun setValue(key: String, value: String) {
        val editor = sharedPreferences.edit()

        editor.putString(key, value)

        editor.apply()
    }

    private fun getValue(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun nativeSet(key: String, value: String) {
        setValue(key, value)
    }

    override fun nativeGet(key: String): String? {
        return getValue(key)
    }

}