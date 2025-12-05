package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepets_spa.ui.navigation.Routes
import com.example.pepets_spa.ui.viewmodel.AppViewModel

@Composable
fun CrearCitaScreen(navController: NavHostController, vm: AppViewModel) {
    val servicios by vm.servicios.collectAsState()
    val mascotas by vm.mascotas.collectAsState()

    var servicioId by remember { mutableStateOf<Long?>(null) }
    var mascotaId by remember { mutableStateOf<Long?>(null) }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        vm.cargarServicios()
        vm.cargarMascotas()
    }

    Scaffold { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(padding)) {

            Text("Reservar Cita", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))

            Text("Servicio")
            DropdownMenuBox(items = servicios.map { it.nombre to it.id!! }, onSelect = { servicioId = it })

            Spacer(Modifier.height(8.dp))
            Text("Mascota")
            DropdownMenuBox(items = mascotas.map { it.nombre to it.id!! }, onSelect = { mascotaId = it })

            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha (yyyy-MM-dd)") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = hora, onValueChange = { hora = it }, label = { Text("Hora (HH:mm)") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(12.dp))
            Button(onClick = {
                if (servicioId != null && mascotaId != null) {
                    vm.crearCita(servicioId!!, fecha, hora)
                    navController.navigate(Routes.CITAS)
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Confirmar cita")
            }
        }
    }
}

@Composable
private fun DropdownMenuBox(items: List<Pair<String, Long>>, onSelect: (Long) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(if (items.isNotEmpty()) items[0].first else "") }
    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedText)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { (label, id) ->
                DropdownMenuItem(text = { Text(label) }, onClick = {
                    selectedText = label
                    expanded = false
                    onSelect(id)
                })
            }
        }
    }
}
