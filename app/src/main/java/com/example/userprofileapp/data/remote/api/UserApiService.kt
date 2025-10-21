package com.example.userprofileapp.data.remote.api

import com.example.userprofileapp.data.remote.dto.UserDTO
import retrofit2.http.GET

interface UserApiService {
    @GET(ApiConstants.User.LIST)
    suspend fun getUsers(): List<UserDTO>
}