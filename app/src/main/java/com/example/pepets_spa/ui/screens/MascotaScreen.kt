package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepets_spa.data.model.Mascota
import com.example.pepets_spa.ui.components.BottomNavBar
import com.example.pepets_spa.ui.viewmodel.AppViewModel

@Composable
fun MascotasScreen(navController: NavHostController, vm: AppViewModel) {
    val mascotas by vm.mascotas.collectAsState()
    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var edadText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { vm.cargarMascotas() }

    Scaffold(bottomBar = { BottomNavBar(navController) }) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(padding)) {

            Text("Mis Mascotas", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = tipo, onValueChange = { tipo = it }, label = { Text("Tipo") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = edadText, onValueChange = { edadText = it.filter { ch -> ch.isDigit() } }, label = { Text("Edad") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                val edad = edadText.toIntOrNull() ?: 0
                vm.agregarMascota(nombre, tipo, edad)
                nombre = ""; tipo = ""; edadText = ""
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Agregar mascota")
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn {
                items(mascotas) { m ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)) {
                        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(m.nombre, style = MaterialTheme.typography.titleMedium)
                                Text("${m.tipo} • ${m.edad} años", style = MaterialTheme.typography.bodyMedium)
                            }
                            TextButton(onClick = { m.id?.let { vm.eliminarMascota(it) } }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}
