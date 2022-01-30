package com.itechart.smg.features.auth.data.repository

import com.itechart.smg.core.dto.RefreshTokenOutputDTO
import com.itechart.smg.core.model.AccessTokens
import com.itechart.smg.features.auth.dto.LoginOutputDTO

interface AuthRepository {
    suspend fun getAccessTokensByLoginData(username: String, password: String): LoginOutputDTO
    suspend fun updateAccessTokens(tokens: AccessTokens): RefreshTokenOutputDTO
    suspend fun removeAccessTokens()
}