package com.gcolina.aristichat.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.first

class GetUserNameUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke(): String = databaseService.getUserName().first()
}
