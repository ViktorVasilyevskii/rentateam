package com.vasilyevskii.rentteamtesttask.repository

import com.vasilyevskii.rentteamtesttask.api.ApiService
import javax.inject.Inject

class UserRepository
@Inject constructor(private val apiService: ApiService){

    fun getUsers() = apiService.getUsers()
}