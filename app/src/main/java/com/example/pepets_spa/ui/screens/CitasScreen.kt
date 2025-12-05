package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepets_spa.ui.components.BottomNavBar
import com.example.pepets_spa.ui.viewmodel.AppViewModel

@Composable
fun CitasScreen(navController: NavHostController, vm: AppViewModel) {
    val citas by vm.citas.collectAsState()

    LaunchedEffect(Unit) { vm.cargarCitas() }

    Scaffold(bottomBar = { BottomNavBar(navController) }) { padding ->
        LazyColumn(modifier = Modifier.padding(12.dp).padding(padding)) {
            items(citas) { c ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Fecha: ${c.fecha} ${c.hora}", style = MaterialTheme.typography.titleSmall)
                        Text("MascotaId: ${c.mascotaId}   ServicioId: ${c.servicioId}", style = MaterialTheme.typography.bodySmall)
                        Text("Estado: ${c.estado}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
