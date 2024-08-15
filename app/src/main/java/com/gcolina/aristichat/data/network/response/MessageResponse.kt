package com.gcolina.aristichat.data.network.response

import com.gcolina.aristichat.domain.model.MessageModel
import com.gcolina.aristichat.domain.model.UserModel

data class MessageResponse(
    val msg: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null,
) {
    fun toDomain(): MessageModel = MessageModel(
        msg = msg.orEmpty(),
        hour = hour ?: "no date",
        date = date.orEmpty(),
        user = UserModel(userName = user?.userName ?: "Guess", admin = user?.admin ?: false)
    )
}

data class UserResponse(val userName: String? = null, val admin: Boolean? = null)
