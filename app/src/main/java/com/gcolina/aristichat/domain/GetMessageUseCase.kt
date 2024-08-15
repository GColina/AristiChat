package com.gcolina.aristichat.domain

import com.gcolina.aristichat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke() = firebaseChatService.getMessages()
}
