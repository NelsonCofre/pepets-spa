package com.example.pepets_spa.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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

    var errorMascota by remember { mutableStateOf("") }
    var errorFecha by remember { mutableStateOf("") }
    var errorHora by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(16.dp)
    ) {
        Text(
            "Agendar Cita",
            style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF333333)),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (mascotas.isNotEmpty()) {
            MascotaDropdown(
                mascotas = mascotas,
                selectedMascotaId = mascotaId,
                onMascotaSelected = {
                    mascotaId = it
                    errorMascota = ""
                }
            )
        } else {
            Text(
                "No tienes mascotas registradas",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF8E8E93)),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        if (errorMascota.isNotEmpty()) {
            Text(errorMascota, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = fecha,
            onValueChange = {
                fecha = it
                errorFecha = ""
            },
            label = { Text("Fecha (dd/mm/yyyy)") },
            placeholder = { Text("Ej: 25/12/2025") },
            isError = errorFecha.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp)
        )
        if (errorFecha.isNotEmpty()) {
            Text(errorFecha, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        OutlinedTextField(
            value = hora,
            onValueChange = {
                hora = it
                errorHora = ""
            },
            label = { Text("Hora (HH:mm)") },
            placeholder = { Text("Ej: 14:30") },
            isError = errorHora.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp)
        )
        if (errorHora.isNotEmpty()) {
            Text(errorHora, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Cancelar", color = Color(0xFF333333))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    // Validaciones
                    var valido = true
                    if (mascotaId == 0) {
                        errorMascota = "Selecciona una mascota"
                        valido = false
                    }
                    if (!fecha.matches(Regex("\\d{2}/\\d{2}/\\d{4}"))) {
                        errorFecha = "Fecha inválida"
                        valido = false
                    }
                    if (!hora.matches(Regex("\\d{2}:\\d{2}"))) {
                        errorHora = "Hora inválida"
                        valido = false
                    }

                    if (valido) {
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
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirmar", color = Color.White)
            }
        }
    }
}


@Composable
fun MascotaDropdown(
    mascotas: List<com.example.pepets_spa.model.Mascota>,
    selectedMascotaId: Int,
    onMascotaSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedNombre = mascotas.firstOrNull { it.id == selectedMascotaId }?.nombre ?: "Selecciona mascota"

    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFBDBDBD), RoundedCornerShape(12.dp)) // <- aquí
                .clickable { expanded = true }
        ) {
            // contenido...
        }

        Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedNombre,
                    color = if (selectedMascotaId == 0) Color(0xFF8E8E93) else Color(0xFF333333)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = Color(0xFF333333)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            mascotas.forEach { mascota ->
                DropdownMenuItem(
                    text = { Text(mascota.nombre) },
                    onClick = {
                        onMascotaSelected(mascota.id)
                        expanded = false
                    }
                )
            }
        }
    }

