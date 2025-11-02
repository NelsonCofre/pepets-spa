package com.example.pepets_spa.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pepets_spa.viewmodel.CitaViewModel

@Composable
fun AppointmentScreen(
    navController: NavController,
    usuarioId: Int,
    citaViewModel: CitaViewModel = viewModel()
) {
    val citas by citaViewModel.obtenerCitasDeUsuario(usuarioId).observeAsState(emptyList())


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Historial de citas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (citas.isEmpty()) {
            Text("No tienes citas agendadas")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(citas) { cita ->
                    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Servicio ID: ${cita.servicioId}")
                            Text("Mascota ID: ${cita.mascotaId}")
                            Text("Fecha: ${cita.fecha}")
                            Text("Hora: ${cita.hora}")
                            Text("Estado: ${cita.estado}")
                        }
                    }
                }
            }
        }
    }
}
