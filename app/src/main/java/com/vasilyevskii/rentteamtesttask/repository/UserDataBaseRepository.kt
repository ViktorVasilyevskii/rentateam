package com.vasilyevskii.rentteamtesttask.repository

import com.vasilyevskii.rentteamtesttask.db.UserDao
import com.vasilyevskii.rentteamtesttask.model.UserDTO
import javax.inject.Inject

class UserDataBaseRepository
@Inject constructor(
    private val userDao: UserDao
){
    fun getAllUsersList() = userDao.getAllUsers()

    fun insertUser(userDTO: UserDTO) = userDao.insertUserDao(userDTO)



}