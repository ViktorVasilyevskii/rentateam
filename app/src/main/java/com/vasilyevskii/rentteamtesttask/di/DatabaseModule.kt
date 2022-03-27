package com.vasilyevskii.rentteamtesttask.di

import android.content.Context
import androidx.room.Room
import com.vasilyevskii.rentteamtesttask.db.UserDao
import com.vasilyevskii.rentteamtesttask.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideChannelDao(userDatabase: UserDatabase) = userDatabase.userDao()

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDatabase{
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
        "UserDTO"
        ).fallbackToDestructiveMigration()
            .build()
    }
}