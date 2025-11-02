package com.example.pepets_spa.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pepets_spa.model.Cita
import com.example.pepets_spa.viewmodel.CitaViewModel
import com.example.pepets_spa.viewmodel.MascotaViewModel
import com.example.pepets_spa.viewmodel.ServicioViewModel
import com.example.pepets_spa.model.Servicio




@Composable
fun AppointmentScreen(
    navController: NavController,
    usuarioId: Int,
    citaViewModel: CitaViewModel = viewModel(),
    mascotaViewModel: MascotaViewModel = viewModel(),
    servicioViewModel: ServicioViewModel = viewModel()
) {
    val citas by citaViewModel.obtenerCitasDeUsuario(usuarioId).observeAsState(emptyList())
    val mascotas by mascotaViewModel.obtenerMascotasDeUsuario(usuarioId).observeAsState(emptyList())
    val servicios: List<Servicio> by servicioViewModel.servicios.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(16.dp)
    ) {
        Text(
            "Historial de citas",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (citas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No tienes citas agendadas",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF8E8E93)
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = citas, key = { it.id }) { cita: Cita ->
                    val mascotaNombre = mascotas.firstOrNull { it.id == cita.mascotaId }?.nombre ?: "Desconocida"
                    val servicioNombre = servicios.firstOrNull { it.id == cita.servicioId }?.nombre ?: "Desconocido"

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    servicioNombre,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color(0xFF6C63FF)
                                )
                                Text(
                                    cita.estado.replaceFirstChar { it.uppercase() },
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = when (cita.estado.lowercase()) {
                                        "pendiente" -> Color(0xFFFFA000)
                                        "confirmada" -> Color(0xFF43A047)
                                        "cancelada" -> Color(0xFFE53935)
                                        else -> Color(0xFF8E8E93)
                                    }
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                "Mascota: $mascotaNombre",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF333333)
                            )
                            Text(
                                "Fecha: ${cita.fecha}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF333333)
                            )
                            Text(
                                "Hora: ${cita.hora}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Botón para cancelar cita
                            if (cita.estado.lowercase() != "cancelada") {
                                Button(
                                    onClick = {
                                        val citaCancelada = cita.copy(estado = "cancelada")
                                        citaViewModel.actualizar(citaCancelada)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Cancelar Cita", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
