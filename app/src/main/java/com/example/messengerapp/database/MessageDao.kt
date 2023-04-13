package com.example.messengerapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MessageDao {
    @Transaction
    @Query("SELECT * FROM Channel")
    fun getAllMessages(): LiveData<ChannelWithMessages>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessage(message: Message?)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createChannel(channel: Channel?): Long

    @Transaction
    @Query("delete from Channel")
    suspend fun clearAll()
}