package com.example.userprofileapp.data.repository

import com.example.userprofileapp.data.mapper.toDomain
import com.example.userprofileapp.data.remote.api.UserApiService
import com.example.userprofileapp.domain.entities.User
import com.example.userprofileapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val response = apiService.getUsers()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}