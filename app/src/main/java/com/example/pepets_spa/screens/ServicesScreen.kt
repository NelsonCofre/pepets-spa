package com.example.pepets_spa.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pepets_spa.model.Servicio
import com.example.pepets_spa.viewmodel.*

@Composable
fun ServicesScreen(
    navController: NavController,
    servicioViewModel: ServicioViewModel = viewModel(),
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val servicios by servicioViewModel.servicios.observeAsState(emptyList())
    val usuarioLogeado by usuarioViewModel.usuarioLogeado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Servicios disponibles",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (servicios.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No hay servicios disponibles",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(servicios) { servicio ->
                    ServicioItem(servicio) { s ->
                        val usuarioId = usuarioLogeado?.id ?: 0
                        navController.navigate("agendarCita/${s.id}/$usuarioId")
                    }
                }
            }
        }
    }
}

@Composable
fun ServicioItem(
    servicio: Servicio,
    onAgendarClick: (Servicio) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                servicio.nombre,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )

            Text(servicio.descripcion, style = MaterialTheme.typography.bodyMedium)

            Text("Precio: $${servicio.precio}", style = MaterialTheme.typography.bodyMedium)

            Text("Tipo: ${servicio.tipo}", style = MaterialTheme.typography.bodyMedium)

            Text("Duración: ${servicio.duracion} minutos", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = { onAgendarClick(servicio) },
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                ) {
                    Text("Agendar")
                }
            }
        }
    }
}

