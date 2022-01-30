package com.itechart.smg.features.auth.data.api

import com.itechart.smg.core.dto.RefreshTokenOutputDTO
import com.itechart.smg.core.model.AccessTokens
import com.itechart.smg.features.auth.dto.LoginOutputDTO

interface AuthClient {
    suspend fun login(username: String, password: String): LoginOutputDTO
    suspend fun logout()
    suspend fun refreshToken(tokens: AccessTokens): RefreshTokenOutputDTO
}