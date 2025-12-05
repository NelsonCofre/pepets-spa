package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepets_spa.ui.components.BottomNavBar
import com.example.pepets_spa.ui.navigation.Routes
import com.example.pepets_spa.ui.viewmodel.AppViewModel

@Composable
fun ServiciosScreen(navController: NavHostController, vm: AppViewModel) {
    val servicios by vm.servicios.collectAsState()

    LaunchedEffect(Unit) { vm.cargarServicios() }

    Scaffold(bottomBar = { BottomNavBar(navController) }) { padding ->
        LazyColumn(modifier = Modifier.padding(12.dp).padding(padding)) {
            items(servicios) { s ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(s.nombre, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(6.dp))
                        Text(s.descripcion ?: "", style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(6.dp))
                        Text("Precio: ${s.precio ?: ""}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { navController.navigate(Routes.CREAR_CITA) }) {
                            Text("Reservar")
                        }
                    }
                }
            }
        }
    }
}
