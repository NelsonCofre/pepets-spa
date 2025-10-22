package com.example.pepets_spa.ui.screens

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pepets_spa.viewmodel.UsuarioViewModel

@Composable
fun LoginScreen(navController: NavController, usuarioViewModel: UsuarioViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    // Animación inicial para el logo
    var startAnimation by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (startAnimation) 1f else 0.8f)
    LaunchedEffect(Unit) { startAnimation = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFFB3E5FC), Color(0xFFFFFFFF)),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1800f)
                )
            )
    ) {
        // Huellitas decorativas
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                repeat(8) { i ->
                    drawCircle(
                        color = Color(0xFF0288D1).copy(alpha = 0.08f),
                        radius = 80f,
                        center = Offset(
                            x = (i * 180).toFloat() % size.width,
                            y = (i * 250).toFloat() % size.height
                        )
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 36.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Logo + Nombre
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Pets,
                    contentDescription = "Logo Pepets Spa",
                    tint = Color(0xFF0288D1),
                    modifier = Modifier
                        .size(90.dp)
                        .scale(scale)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Pepets Spa",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0288D1),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.shadow(8.dp, spotColor = Color(0xFF81D4FA))
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Bienvenido de nuevo 🐾",
                    fontSize = 16.sp,
                    color = Color(0xFF4F4F4F),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Tarjeta del login
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .shadow(12.dp, RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0288D1),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo electrónico") },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF0288D1),
                            unfocusedBorderColor = Color(0xFFB0BEC5),
                            focusedLabelColor = Color(0xFF0288D1)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF0288D1),
                            unfocusedBorderColor = Color(0xFFB0BEC5),
                            focusedLabelColor = Color(0xFF0288D1)
                        )
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    // Botón con gradiente
                    Button(
                        onClick = {
                            usuarioViewModel.validarLogin(email, password) { success ->
                                if (success) {
                                    navController.navigate("home")
                                } else {
                                    errorMsg = "Email o contraseña incorrectos"
                                    Toast.makeText(
                                        navController.context,
                                        "Email o contraseña incorrectos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues()
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(Color(0xFF0288D1), Color(0xFF4FC3F7))
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Iniciar sesión",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (errorMsg.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = errorMsg, color = Color.Red)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "¿No tienes cuenta? Regístrate aquí",
                        color = Color(0xFF0288D1),
                        modifier = Modifier.clickable { navController.navigate("register") },
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
        }
    }
}
