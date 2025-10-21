package com.example.userprofileapp.domain.usecase

import com.example.userprofileapp.domain.entities.User
import com.example.userprofileapp.domain.repository.UserRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private lateinit var useCase: GetUsersUseCase
    private val mockRepository: UserRepository = mockk()

    @Before
    fun setup() {
        useCase = GetUsersUseCase(mockRepository)
    }

    @Test
    fun `invoke returns success when repository returns success`() = runTest {
        // Given
        val expectedUsers = listOf(
            User(1, "John Doe", "Acme Corp", "john", "john@test.com",
                "123 St", "12345", "CA", "USA", "555-1234", "photo.jpg")
        )
        coEvery { mockRepository.getUsers() } returns Result.success(expectedUsers)

        // When
        val result = useCase()

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedUsers)
        coVerify(exactly = 1) { mockRepository.getUsers() }
    }

    @Test
    fun `invoke returns failure when repository returns failure`() = runTest {
        // Given
        val exception = Exception("Network error")
        coEvery { mockRepository.getUsers() } returns Result.failure(exception)

        // When
        val result = useCase()

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(exception)
        coVerify(exactly = 1) { mockRepository.getUsers() }
    }
}