package com.example.userprofileapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.userprofileapp.presentation.screens.SplashScreen
import com.example.userprofileapp.presentation.screens.UserDetailScreen
import com.example.userprofileapp.presentation.screens.UserListScreen
import com.example.userprofileapp.presentation.viewmodel.UserViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToList = {
                    navController.navigate(Screen.UserList.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.UserList.route) {
            val viewModel: UserViewModel = hiltViewModel()
            UserListScreen(
                viewModel = viewModel,
                onUserClick = { userId ->
                    navController.navigate(Screen.UserDetail.createRoute(userId))
                }
            )
        }

        composable(
            route = Screen.UserDetail.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            val viewModel: UserViewModel = hiltViewModel()
            UserDetailScreen(
                userId = userId,
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}