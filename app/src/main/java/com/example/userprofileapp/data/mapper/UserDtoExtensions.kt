package com.example.userprofileapp.data.mapper

import com.example.userprofileapp.data.remote.dto.UserDTO
import com.example.userprofileapp.domain.entities.User

fun UserDTO.toDomain(): User {
    return User(
        id = id ?: 0,
        name = name ?: "",
        company = company ?: "",
        username = username ?: "",
        email = email ?: "",
        address = address ?: "",
        zip = zip ?: "",
        state = state ?: "",
        country = country ?: "",
        phone = phone ?: "",
        photo = photo ?: ""
    )
}