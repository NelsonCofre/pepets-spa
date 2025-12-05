package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepets_spa.ui.components.BottomNavBar
import com.example.pepets_spa.ui.viewmodel.AppViewModel

@Composable
fun HomeScreen(navController: NavHostController, vm: AppViewModel) {
    // Load some data for home if you want
    LaunchedEffect(Unit) {
        vm.cargarServicios()
        vm.cargarMascotas()
        vm.cargarCitas()
    }

    Scaffold(bottomBar = { BottomNavBar(navController) }) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(padding)) {
            val user = vm.usuario.collectAsState().value
            Text("Bienvenido ${user?.nombre ?: ""}", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text("Usa la barra inferior para navegar")
        }
    }
}
