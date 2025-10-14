package com.example.pepets_spa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pepets_spa.ui.screens.*
import com.example.pepets_spa.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel
) {
    NavHost(navController = navController, startDestination = "register") {

        composable("login") {
            LoginScreen(navController, usuarioViewModel)
        }

        composable("register") {
            RegisterScreen(navController, usuarioViewModel)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("pets") {
            PetsScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable("services") {
            ServicesScreen(navController)
        }

        composable("appointments") {
            AppointmentScreen(navController)
        }
    }
}
