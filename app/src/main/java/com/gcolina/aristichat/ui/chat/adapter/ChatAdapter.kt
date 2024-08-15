package com.gcolina.aristichat.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.aristichat.databinding.ItemChatMeBinding
import com.gcolina.aristichat.databinding.ItemChatOtherBinding
import com.gcolina.aristichat.domain.model.MessageModel

class ChatAdapter(var messageList: MutableList<MessageModel>, private var userName: String = "") :
    RecyclerView.Adapter<ChatViewHolder>() {

    companion object {
        const val SENT_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }

    fun updateList(list: MutableList<MessageModel>, name: String) {
        userName = name
        messageList = list
        notifyItemInserted(messageList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = when (viewType) {
            SENT_MESSAGE -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            RECEIVED_MESSAGE -> {
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            else -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position], getItemViewType(position))
    }

    override fun getItemViewType(position: Int): Int =
        if (messageList[position].user.userName == userName) {
            SENT_MESSAGE
        } else {
            RECEIVED_MESSAGE
        }
}
