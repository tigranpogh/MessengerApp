package com.example.messengerapp.di

import android.app.Application
import androidx.room.Room
import com.example.messengerapp.database.AppDatabase
import com.example.messengerapp.database.MessageDao
import com.example.messengerapp.repo.MessengerRepository
import com.example.messengerapp.repo.MessengerRepositoryImpl
import com.example.messengerapp.usecase.MessengerUseCase
import com.example.messengerapp.usecase.MessengerUseCaseImpl
import com.example.messengerapp.ui.viewmodel.MessengerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "database").build()
}

private fun provideDao(appDatabase: AppDatabase): MessageDao? {
    return appDatabase.messageDAO()
}

val appModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val messengerModule = module {
    single<MessengerRepository> {
        return@single MessengerRepositoryImpl(get())
    }

    single<MessengerUseCase> {
        return@single MessengerUseCaseImpl(get())
    }

    viewModel {
        MessengerViewModel(get())
    }
}