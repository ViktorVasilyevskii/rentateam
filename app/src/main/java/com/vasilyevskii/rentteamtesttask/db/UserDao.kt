package com.vasilyevskii.rentteamtesttask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vasilyevskii.rentteamtesttask.model.UserDTO
import io.reactivex.Completable
import io.reactivex.Maybe
import org.jetbrains.annotations.NotNull

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDTO")
    fun getAllUsers(): Maybe<List<UserDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDao(userDTO: UserDTO)
}