package com.example.userprofileapp.presentation.model

sealed class UserIntent {
    object LoadUsers : UserIntent()
}