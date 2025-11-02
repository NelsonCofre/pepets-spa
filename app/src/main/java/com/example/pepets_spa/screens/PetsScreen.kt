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
import com.example.pepets_spa.model.Mascota
import com.example.pepets_spa.viewmodel.MascotaViewModel
import com.example.pepets_spa.viewmodel.UsuarioViewModel

@Composable
fun PetsScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    mascotaViewModel: MascotaViewModel = viewModel()
) {
    val usuarioLogeado = usuarioViewModel.usuarioLogeado.value
    val usuarioId = usuarioLogeado?.id ?: return

    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }

    val mascotas: List<Mascota> by mascotaViewModel.obtenerMascotasDeUsuario(usuarioId)
        .observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pantalla de Mascotas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        // 🔹 Formulario más compacto
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = especie,
                    onValueChange = { especie = it },
                    label = { Text("Especie") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = edad,
                    onValueChange = { edad = it.filter { c -> c.isDigit() } },
                    label = { Text("Edad") },
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
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (nombre.isNotBlank() && especie.isNotBlank() && edad.isNotBlank()) {
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Mascota")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 🔹 Lista de mascotas ocupa más espacio
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            if (mascotas.isEmpty()) {
                Text(
                    "No tienes mascotas registradas",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(mascotas) { mascota ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(mascota.nombre, style = MaterialTheme.typography.titleMedium)
                                Text("Especie: ${mascota.especie}")
                                Text("Edad: ${mascota.edad}")
                                mascota.raza?.let { Text("Raza: $it") }
                            }
                        }
                    }
                }
            }
        }
    }
}

