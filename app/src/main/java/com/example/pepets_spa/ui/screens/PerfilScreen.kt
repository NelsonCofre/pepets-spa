package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepets_spa.ui.viewmodel.AppViewModel
import com.example.pepets_spa.ui.navigation.Routes

@Composable
fun PerfilScreen(navController: NavHostController, vm: AppViewModel) {
    val user by vm.usuario.collectAsState()

    Scaffold(bottomBar = { /* you can reuse bottom if desired */ }) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(padding)) {

            Text("Perfil", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))

            Text("Nombre: ${user?.nombre ?: ""}")
            Text("Email: ${user?.email ?: ""}")

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                vm.logout()
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }) {
                Text("Cerrar sesión")
            }
        }
    }
}
