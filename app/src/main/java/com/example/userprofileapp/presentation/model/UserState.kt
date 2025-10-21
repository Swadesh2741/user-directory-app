package com.example.userprofileapp.presentation.model

import com.example.userprofileapp.domain.entities.User

data class UserState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
