package com.example.messengerapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messengerapp.database.Channel
import com.example.messengerapp.database.ChannelWithMessages
import com.example.messengerapp.database.Message
import com.example.messengerapp.usecase.MessengerUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MessengerViewModel(private val messengerUseCase: MessengerUseCase) : ViewModel() {

    var channelId: Long = 0

    fun saveMessage(message: Message) = viewModelScope.launch {
        messengerUseCase.saveMessage(message)
    }

    fun createChannel(channel: Channel) {
        runBlocking {
            channelId = messengerUseCase.createChannel(channel)
        }
    }

    fun clearData() = viewModelScope.launch {
        messengerUseCase.clearData()
    }

    fun getSavedMessages(): LiveData<ChannelWithMessages> = messengerUseCase.getSavedMessages()
}