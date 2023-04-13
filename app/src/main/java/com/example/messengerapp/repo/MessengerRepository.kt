package com.example.messengerapp.repo

import androidx.lifecycle.LiveData
import com.example.messengerapp.database.Channel
import com.example.messengerapp.database.ChannelWithMessages
import com.example.messengerapp.database.Message

interface MessengerRepository {
    suspend fun saveMessage(message: Message)

    suspend fun createChannel(channel: Channel): Long

    suspend fun clearData()

    fun getSavedMessages(): LiveData<ChannelWithMessages>
}