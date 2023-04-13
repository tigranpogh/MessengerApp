package com.example.messengerapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.messengerapp.ui.adapter.MessengerAdapter
import com.example.messengerapp.R
import com.example.messengerapp.database.Channel
import com.example.messengerapp.database.Message
import com.example.messengerapp.databinding.FragmentMessengerBinding
import com.example.messengerapp.ui.viewmodel.MessengerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessengerFragment : Fragment() {

    private var _binding: FragmentMessengerBinding? = null
    private val binding get() = _binding!!
    private val messengerViewModel: MessengerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessengerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAllMessagesObserver()

        binding.btnSend.setOnClickListener {
            onSendClick()
        }

        binding.btnClear.setOnClickListener {
            onClearClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAllMessagesObserver() {
        messengerViewModel.getSavedMessages().observe(viewLifecycleOwner) { channelResponse ->
            if (channelResponse == null) {
                initMessengerAdapter(emptyList())
            } else {
                messengerViewModel.channelId = channelResponse.channel.channelId
                val messagesList = channelResponse.messages.map { it.message }
                initMessengerAdapter(messagesList)
            }
        }
    }

    private fun initMessengerAdapter(messagesList: List<String>) {
        val messengerAdapter = MessengerAdapter()
        binding.rvMessenger.adapter = messengerAdapter
        messengerAdapter.setItems(messagesList)
        binding.rvMessenger.scrollToPosition(messagesList.size -1)
    }

    private fun onSendClick() {
        val typedText = binding.edtText.text.toString()
        binding.edtText.text.clear()
        if (typedText.isEmpty()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.please_type_message),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if (messengerViewModel.channelId == 0L) {
                messengerViewModel.createChannel(Channel(name = getString(R.string.channel_name)))
            }
            messengerViewModel.saveMessage(
                Message(
                    message = typedText,
                    messageChannelId = messengerViewModel.channelId
                )
            )
        }
    }

    private fun onClearClick() {
        messengerViewModel.clearData()
        messengerViewModel.channelId = 0L
    }
}