package com.example.pepets_spa.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pepets_spa.model.Usuario
import com.example.pepets_spa.viewmodel.UsuarioViewModel

@Composable
fun RegisterScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val context = LocalContext.current

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

        // BOTÓN FUNCIONAL
        Button(
            onClick = {
                if (nombre.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
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
                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1)),
            shape = RoundedCornerShape(12.dp)
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

