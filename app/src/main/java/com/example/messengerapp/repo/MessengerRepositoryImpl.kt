package com.example.messengerapp.repo

import androidx.lifecycle.LiveData
import com.example.messengerapp.database.Channel
import com.example.messengerapp.database.ChannelWithMessages
import com.example.messengerapp.database.Message
import com.example.messengerapp.database.MessageDao

class MessengerRepositoryImpl(private val messageDao: MessageDao) : MessengerRepository {
    override suspend fun saveMessage(message: Message) {
        messageDao.insertMessage(message)
    }

    override suspend fun createChannel(channel: Channel): Long {
        return messageDao.createChannel(channel)
    }

    override suspend fun clearData() {
        messageDao.clearAll()
    }

    override fun getSavedMessages(): LiveData<ChannelWithMessages> {
        return messageDao.getAllMessages()
    }

}