package com.itechart.smg.core.utils.token

import com.itechart.smg.core.model.AccessTokens

class AccessTokensProvider(
    private val keystoreProvider: DeviceKeystoreProvider
) {
    private companion object {
        const val SMG_TOKEN_KEY = "SMG_AUTH_TOKEN"
        const val SMG_REFRESH_TOKEN_KEY = "SMG_AUTH_REFRESH_TOKEN"
    }

    fun getAccessTokens(): AccessTokens? {
        val token = keystoreProvider.nativeGet(SMG_TOKEN_KEY)
        val refreshToken = keystoreProvider.nativeGet(SMG_REFRESH_TOKEN_KEY)

        if(token == null || refreshToken == null) {
            return null
        }

        return AccessTokens(token, refreshToken)
    }

    fun setAccessTokens(accessTokens: AccessTokens) {
        keystoreProvider.nativeSet(SMG_TOKEN_KEY, accessTokens.token)
        keystoreProvider.nativeSet(SMG_REFRESH_TOKEN_KEY, accessTokens.refreshToken)
    }

    fun clearTokens() {
        setAccessTokens(AccessTokens("", ""))
    }
}