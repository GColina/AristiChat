package com.gcolina.aristichat.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcolina.aristichat.R
import com.gcolina.aristichat.databinding.FragmentChatBinding
import com.gcolina.aristichat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        initUi()
        return binding.root
    }

    private fun initUi() {
        setUpMessages()
        subscribeToMessages()
        initListeners()
    }

    private fun setUpToolbar() {
        val name = viewModel.name
        binding.tvUserName.text = "Bienvenido $name"
    }

    private fun setUpMessages() {
        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvMessage.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            viewModel.logout {
                findNavController().navigate(R.id.action_back)
            }
        }
        binding.btnSendMsg.setOnClickListener {
            val msg = binding.etChat.text.toString()
            if (msg.isNotEmpty()) {
                viewModel.sendMessage(msg)
            }
            binding.etChat.text.clear()
        }
    }

    private fun subscribeToMessages() {
        lifecycleScope.launch {
            viewModel.messageList.collect {
                chatAdapter.updateList(it.toMutableList(), viewModel.name)
                setUpToolbar()
                binding.rvMessage.scrollToPosition(chatAdapter.messageList.size - 1)
            }
        }
    }
}
