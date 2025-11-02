package com.example.pepets_spa.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pepets_spa.model.Usuario
import com.example.pepets_spa.viewmodel.UsuarioViewModel

@Composable
fun RegisterScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val context = androidx.compose.ui.platform.LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Pets,
            contentDescription = "Logo",
            tint = Color(0xFF0288D1),
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Pepets Spa", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1))
        Spacer(modifier = Modifier.height(8.dp))
        Text("¡Regístrate y consiente a tu mascota!", fontSize = 16.sp, color = Color(0xFF4F4F4F))

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Validaciones adicionales
                when {
                    nombre.isBlank() || email.isBlank() || password.isBlank() -> {
                        Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                    }
                    nombre.length < 3 -> {
                        Toast.makeText(context, "El nombre debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show()
                    }
                    !email.contains("@") || !email.contains(".") -> {
                        Toast.makeText(context, "Correo inválido: debe contener '@' y un dominio válido", Toast.LENGTH_SHORT).show()
                    }
                    password.length < 6 -> {
                        Toast.makeText(context, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Verificar si el correo ya existe
                        usuarioViewModel.existeUsuario(email) { existe ->
                            if (existe) {
                                Toast.makeText(context, "Ya existe un usuario con ese correo", Toast.LENGTH_SHORT).show()
                            } else {
                                val nuevoUsuario = Usuario(
                                    nombre = nombre,
                                    email = email,
                                    password = password
                                )
                                usuarioViewModel.insertar(nuevoUsuario)
                                Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                                navController.navigate("login")
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1)),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
        ) {
            Text("Registrar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "¿Ya tienes cuenta? Inicia sesión",
            color = Color(0xFF0288D1),
            modifier = Modifier.clickable { navController.navigate("login") }
        )
    }
}
