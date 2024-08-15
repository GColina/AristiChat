package com.gcolina.aristichat.domain

import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    suspend fun saveUserName(nickname: String)
    suspend fun clear()
    fun getUserName(): Flow<String>
}
