package com.example.pepets_spa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pepets_spa.ui.screens.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("pets") { PetsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("services") { ServicesScreen(navController) }
        composable("appointments") { AppointmentScreen(navController) }
    }
}
