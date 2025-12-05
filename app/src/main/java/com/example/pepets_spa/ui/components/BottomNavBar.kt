package com.example.pepets_spa.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pepets_spa.ui.navigation.Routes

data class BottomItem(val label: String, val route: String, val icon: ImageVector)

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomItem("Home", Routes.HOME, Icons.Default.Home),
        BottomItem("Mascotas", Routes.MASCOTAS, Icons.Default.Pets),
        BottomItem("Servicios", Routes.SERVICIOS, Icons.Default.List),
        BottomItem("Citas", Routes.CITAS, Icons.Default.DateRange),
        BottomItem("Perfil", Routes.PERFIL, Icons.Default.Person)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // avoid building up back stack for bottom nav
                            popUpTo(Routes.HOME) { inclusive = false }
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
