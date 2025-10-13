package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Pantalla Principal (Home)", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("pets") }) {
                Text("Ver Mascotas")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("services") }) {
                Text("Servicios")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("appointments") }) {
                Text("Citas")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("profile") }) {
                Text("Perfil")
            }
        }
    }
}
