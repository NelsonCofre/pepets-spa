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
import com.example.pepets_spa.model.Mascota
import com.example.pepets_spa.viewmodel.CitaViewModel
import com.example.pepets_spa.viewmodel.MascotaViewModel
import com.example.pepets_spa.viewmodel.UsuarioViewModel

@Composable
fun PetsScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    mascotaViewModel: MascotaViewModel = viewModel(),
    citaViewModel: CitaViewModel = viewModel() // <-- se agrega aquí
) {
    val usuarioLogeado = usuarioViewModel.usuarioLogeado.value
    val usuarioId = usuarioLogeado?.id ?: return

    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }

    var errorNombre by remember { mutableStateOf("") }
    var errorEspecie by remember { mutableStateOf("") }
    var errorEdad by remember { mutableStateOf("") }

    val mascotas: List<Mascota> by mascotaViewModel.obtenerMascotasDeUsuario(usuarioId)
        .observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(16.dp)
    ) {
        Text(
            "Mis Mascotas",
            style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF4A4A4A)),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Formulario de registro de mascota
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {
                            nombre = it
                            if (it.isNotBlank()) errorNombre = ""
                        },
                        label = { Text("Nombre") },
                        isError = errorNombre.isNotEmpty(),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = especie,
                        onValueChange = {
                            especie = it
                            if (it.isNotBlank()) errorEspecie = ""
                        },
                        label = { Text("Especie") },
                        isError = errorEspecie.isNotEmpty(),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (errorNombre.isNotEmpty() || errorEspecie.isNotEmpty()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        if (errorNombre.isNotEmpty()) {
                            Text(errorNombre, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        if (errorEspecie.isNotEmpty()) {
                            Text(errorEspecie, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = edad,
                        onValueChange = {
                            edad = it.filter { c -> c.isDigit() }
                            if (edad.isNotBlank()) errorEdad = ""
                        },
                        label = { Text("Edad") },
                        isError = errorEdad.isNotEmpty(),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = raza,
                        onValueChange = { raza = it },
                        label = { Text("Raza (opcional)") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (errorEdad.isNotEmpty()) {
                    Text(errorEdad, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        var valido = true
                        if (nombre.isBlank()) {
                            errorNombre = "El nombre es obligatorio"
                            valido = false
                        }
                        if (especie.isBlank()) {
                            errorEspecie = "La especie es obligatoria"
                            valido = false
                        }
                        if (edad.isBlank() || edad.toIntOrNull() == null || edad.toInt() <= 0) {
                            errorEdad = "Edad inválida"
                            valido = false
                        }

                        if (valido) {
                            mascotaViewModel.insertar(
                                Mascota(
                                    nombre = nombre,
                                    especie = especie,
                                    edad = edad.toInt(),
                                    raza = raza.ifBlank { null },
                                    propietarioId = usuarioId,
                                    imageRes = null
                                )
                            )
                            nombre = ""
                            especie = ""
                            edad = ""
                            raza = ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF))
                ) {
                    Text("Agregar Mascota", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de mascotas
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            if (mascotas.isEmpty()) {
                Text(
                    "No tienes mascotas registradas",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF8E8E93)),
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(mascotas) { mascota ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(mascota.nombre, style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF333333)))
                                    Text("Especie: ${mascota.especie}", style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF616161)))
                                    Text("Edad: ${mascota.edad}", style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF616161)))
                                    mascota.raza?.let { Text("Raza: $it", style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF616161))) }
                                }

                                Button(
                                    onClick = {
                                        // Eliminar todas las citas asociadas a esta mascota
                                        citaViewModel.eliminarCitasPorMascota(mascota.id)
                                        // Eliminar la mascota
                                        mascotaViewModel.eliminar(mascota)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B)),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Eliminar", color = Color.White)
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
