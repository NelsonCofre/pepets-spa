package com.example.pepets_spa.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pepets_spa.model.Cita
import com.example.pepets_spa.viewmodel.CitaViewModel
import com.example.pepets_spa.viewmodel.MascotaViewModel

@Composable
fun AgendarCitaScreen(
    navController: NavController,
    servicioId: Int,
    usuarioId: Int,
    citaViewModel: CitaViewModel = viewModel(),
    mascotaViewModel: MascotaViewModel = viewModel()
) {
    val mascotas by mascotaViewModel.obtenerMascotasDeUsuario(usuarioId).observeAsState(emptyList())
    var mascotaId by remember { mutableStateOf(0) }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Agendar cita", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (mascotas.isNotEmpty()) {
            Box {
                OutlinedTextField(
                    value = mascotas.firstOrNull { it.id == mascotaId }?.nombre ?: "Selecciona mascota",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    label = { Text("Mascota") },
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    mascotas.forEach { mascota ->
                        DropdownMenuItem(
                            text = { Text(mascota.nombre) },
                            onClick = {
                                mascotaId = mascota.id
                                expanded = false
                            }
                        )
                    }
                }
            }
        } else {
            Text("No tienes mascotas registradas")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha (dd/mm/yyyy)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = hora,
            onValueChange = { hora = it },
            label = { Text("Hora (HH:mm)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (mascotaId != 0 && fecha.isNotEmpty() && hora.isNotEmpty()) {
                    val cita = Cita(
                        usuarioId = usuarioId,
                        mascotaId = mascotaId,
                        servicioId = servicioId,
                        fecha = fecha,
                        hora = hora
                    )
                    citaViewModel.insertar(cita)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Confirmar")
        }
    }
}
