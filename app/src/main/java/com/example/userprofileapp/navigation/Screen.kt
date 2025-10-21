package com.example.userprofileapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object UserList : Screen("user_list")
    object UserDetail : Screen("user_detail/{userId}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }
}