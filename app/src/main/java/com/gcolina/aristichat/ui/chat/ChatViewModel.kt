package com.gcolina.aristichat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcolina.aristichat.domain.GetMessageUseCase
import com.gcolina.aristichat.domain.GetUserNameUseCase
import com.gcolina.aristichat.domain.SendMessageUseCase
import com.gcolina.aristichat.domain.model.LogoutUseCase
import com.gcolina.aristichat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel
@Inject
constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    var name: String = ""

    init {
        getUserName()
        getMessages()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            name = getUserNameUseCase()
            Log.i("----------aris", "nickname $name")
        }
    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList

    private fun getMessages() {
        viewModelScope.launch {
            val result = getMessageUseCase()
            result.collect {
                Log.d("aris tuto", "la info es $it")
                _messageList.value = it
            }
        }
    }

    fun sendMessage(msg: String) {
        sendMessageUseCase(msg, name)
    }

    fun logout(onViewFinish: () -> Unit) {
        viewModelScope.launch {
            async { logoutUseCase() }.await()
            onViewFinish()
        }
    }
}
