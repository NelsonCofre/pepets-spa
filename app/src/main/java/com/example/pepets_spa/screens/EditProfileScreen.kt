package com.example.pepets_spa.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pepets_spa.viewmodel.UsuarioViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val usuario = usuarioViewModel.usuarioLogeado.value
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf(usuario?.nombre ?: "") }
    var email by remember { mutableStateOf(usuario?.email ?: "") }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0288D1),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF2F8FF)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    when {
                        nombre.isBlank() -> Toast.makeText(context, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                        nombre.length < 3 -> Toast.makeText(context, "El nombre debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show()
                        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Toast.makeText(context, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
                        usuario == null -> Toast.makeText(context, "Error: no se encontró el usuario", Toast.LENGTH_SHORT).show()
                        else -> {
                            isLoading = true
                            scope.launch {
                                usuarioViewModel.existeUsuario(email.trim()) { existe ->
                                    isLoading = false
                                    // ⚠️ Si el email pertenece a otro usuario, no permitir el cambio
                                    if (existe && email.trim() != usuario.email) {
                                        Toast.makeText(context, "Este correo ya está registrado en otra cuenta", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val actualizado = usuario.copy(
                                            nombre = nombre.trim(),
                                            email = email.trim()
                                        )
                                        usuarioViewModel.actualizar(actualizado)
                                        usuarioViewModel.usuarioLogeado.value = actualizado
                                        Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1))
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(22.dp))
                } else {
                    Text("Guardar cambios", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
