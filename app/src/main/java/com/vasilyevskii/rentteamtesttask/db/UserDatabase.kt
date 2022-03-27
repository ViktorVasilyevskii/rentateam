package com.vasilyevskii.rentteamtesttask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vasilyevskii.rentteamtesttask.model.UserDTO

@Database(
    entities = [UserDTO::class],
    version = 2, exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}