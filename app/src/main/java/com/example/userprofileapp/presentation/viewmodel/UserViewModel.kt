package com.example.userprofileapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userprofileapp.domain.usecase.GetUsersUseCase
import com.example.userprofileapp.presentation.model.UserIntent
import com.example.userprofileapp.presentation.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state.asStateFlow()

    init {
        handleIntent(UserIntent.LoadUsers)
    }

    fun handleIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.LoadUsers -> loadUsers()
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            getUsersUseCase().fold(
                onSuccess = { users ->
                    _state.value = _state.value.copy(
                        users = users,
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Unknown error occurred"
                    )
                }
            )
        }
    }

    fun retryLoading() {
        handleIntent(UserIntent.LoadUsers)
    }
}