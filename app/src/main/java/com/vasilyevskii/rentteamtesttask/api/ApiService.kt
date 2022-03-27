package com.vasilyevskii.rentteamtesttask.api

import com.vasilyevskii.rentteamtesttask.model.ResponseApi
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Single<ResponseApi>
}