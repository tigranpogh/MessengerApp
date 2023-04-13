package com.example.messengerapp.usecase

import androidx.lifecycle.LiveData
import com.example.messengerapp.database.Channel
import com.example.messengerapp.database.ChannelWithMessages
import com.example.messengerapp.database.Message
import com.example.messengerapp.repo.MessengerRepository

class MessengerUseCaseImpl(private val messengerRepository: MessengerRepository) :
    MessengerUseCase {
    override suspend fun saveMessage(message: Message) {
        messengerRepository.saveMessage(message)
    }

    override suspend fun createChannel(channel: Channel): Long {
        return messengerRepository.createChannel(channel)
    }

    override suspend fun clearData() {
        messengerRepository.clearData()
    }

    override fun getSavedMessages(): LiveData<ChannelWithMessages> {
        return messengerRepository.getSavedMessages()
    }
}