package com.gcolina.aristichat.domain

import com.gcolina.aristichat.data.network.FirebaseChatService
import com.gcolina.aristichat.data.network.dto.MessageDto
import com.gcolina.aristichat.data.network.dto.UserDto
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {
    operator fun invoke(message: String, nickname: String) {
        // Todas estas variables realmente son innecesarias, por que se podrian ir concadenando,
        // pero asi es mucho mas facil de visuializar yu de aprender.

        val calendar = Calendar.getInstance()
        val currentMsg = message
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val userDto = UserDto(nickname, false)

        val messageDto = MessageDto(
            msg = currentMsg,
            hour = "$hour:$min",
            date = "$day/$month/$year",
            user = userDto
        )

        firebaseChatService.sendMsgToFirebase(messageDto)
    }
}
