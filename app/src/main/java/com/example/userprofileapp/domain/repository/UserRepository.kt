package com.example.userprofileapp.domain.repository

import com.example.userprofileapp.domain.entities.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
}