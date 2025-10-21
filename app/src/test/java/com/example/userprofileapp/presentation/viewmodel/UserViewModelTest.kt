package com.example.userprofileapp.presentation.viewmodel


import com.example.userprofileapp.data.remote.api.UserApiService
import com.example.userprofileapp.data.remote.dto.UserDTO
import com.example.userprofileapp.data.repository.UserRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl
    private val mockApiService: UserApiService = mockk()

    @Before
    fun setup() {
        repository = UserRepositoryImpl(mockApiService)
    }

    @Test
    fun `getUsers returns success with mapped users when API call succeeds`() = runTest {
        // Given
        val userDTOs = listOf(
            UserDTO(1, "John Doe", "Acme", "john", "john@test.com",
                "123 St", "12345", "CA", "USA", "555-1234", "photo.jpg")
        )
        coEvery { mockApiService.getUsers() } returns userDTOs

        // When
        val result = repository.getUsers()

        // Then
        assertThat(result.isSuccess).isTrue()
        val users = result.getOrNull()
        assertThat(users).hasSize(1)
        assertThat(users?.first()?.name).isEqualTo("John Doe")
        coVerify(exactly = 1) { mockApiService.getUsers() }
    }

    @Test
    fun `getUsers returns failure when API call throws exception`() = runTest {
        // Given
        val exception = IOException("Network error")
        coEvery { mockApiService.getUsers() } throws exception

        // When
        val result = repository.getUsers()

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isInstanceOf(IOException::class.java)
        coVerify(exactly = 1) { mockApiService.getUsers() }
    }

    @Test
    fun `getUsers handles null values in DTO correctly`() = runTest {
        // Given
        val userDTOs = listOf(
            UserDTO(null, null, null, null, null, null, null, null, null, null, null)
        )
        coEvery { mockApiService.getUsers() } returns userDTOs

        // When
        val result = repository.getUsers()

        // Then
        assertThat(result.isSuccess).isTrue()
        val users = result.getOrNull()
        assertThat(users?.first()?.id).isEqualTo(0)
        assertThat(users?.first()?.name).isEqualTo("")
        assertThat(users?.first()?.email).isEqualTo("")
    }
}