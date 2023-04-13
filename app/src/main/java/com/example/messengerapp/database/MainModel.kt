package com.example.messengerapp.database

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE

@Entity
data class Channel(
    @PrimaryKey(autoGenerate = true) val channelId: Long = 0,
    val name: String
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = Channel::class,
        parentColumns = ["channelId"],
        childColumns = ["messageChannelId"],
        onDelete = CASCADE
    )]
)

data class Message(
    @PrimaryKey(autoGenerate = true) val messageId: Long = 0,
    val messageChannelId: Long,
    val message: String
)

data class ChannelWithMessages(
    @Embedded val channel: Channel,
    @Relation(
        parentColumn = "channelId",
        entityColumn = "messageChannelId"
    )
    val messages: List<Message>
)