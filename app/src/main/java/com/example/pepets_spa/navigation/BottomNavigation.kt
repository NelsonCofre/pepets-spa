package com.example.pepets_spa.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val icon: ImageVector, val title: String) {
    object Pets : BottomNavScreen("pets", Icons.Default.Pets, "Mascotas")
    object Services : BottomNavScreen("services", Icons.Default.MiscellaneousServices, "Servicios")
    object Appointments : BottomNavScreen("appointments", Icons.Default.Event, "Citas")
    object Profile : BottomNavScreen("profile", Icons.Default.Person, "Perfil")
}
