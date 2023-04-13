package com.example.messengerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Channel::class, Message::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDAO(): MessageDao?
}