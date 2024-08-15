package com.gcolina.aristichat.domain.model

import com.gcolina.aristichat.domain.DatabaseService
import javax.inject.Inject

class LogoutUseCase@Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke() {
        databaseService.clear()
    }
}
