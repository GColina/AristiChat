package com.gcolina.aristichat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gcolina.aristichat.databinding.ItemChatMeBinding
import com.gcolina.aristichat.databinding.ItemChatOtherBinding
import com.gcolina.aristichat.domain.model.MessageModel

class ChatViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when (itemViewType) {
            ChatAdapter.SENT_MESSAGE -> bindSentMessage(messageModel)
            ChatAdapter.RECEIVED_MESSAGE -> bindReceiveMessage(messageModel)
        }
    }

    private fun bindReceiveMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOtherBinding
        currentBinding.tvDate.text = messageModel.date
        currentBinding.tvMessage.text = messageModel.msg
        currentBinding.tvHour.text = messageModel.hour
        currentBinding.tvName.text = messageModel.user.userName
    }

    private fun bindSentMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding
        currentBinding.tvDate.text = messageModel.date
        currentBinding.tvMessage.text = messageModel.msg
        currentBinding.tvHour.text = messageModel.hour
    }
}
