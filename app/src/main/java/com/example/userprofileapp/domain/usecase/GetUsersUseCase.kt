package com.example.userprofileapp.domain.usecase

import com.example.userprofileapp.domain.entities.User
import com.example.userprofileapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return repository.getUsers()
    }
}