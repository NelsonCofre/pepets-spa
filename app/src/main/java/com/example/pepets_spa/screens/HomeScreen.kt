package com.example.pepets_spa.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, userName: String = "Usuario") {

    // Animación para el logo
    var startAnimation by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (startAnimation) 1f else 0.8f)
    LaunchedEffect(Unit) { startAnimation = true }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pepets Spa",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0288D1),
                        modifier = Modifier.shadow(8.dp, spotColor = Color(0xFF81D4FA))
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Cerrar sesión",
                            tint = Color(0xFF0288D1)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
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
                .padding(paddingValues)
        ) {
            // Fondo con huellitas
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawIntoCanvas {
                    repeat(10) { i ->
                        drawCircle(
                            color = Color(0xFF0288D1).copy(alpha = 0.07f),
                            radius = 85f,
                            center = Offset(
                                x = (i * 190).toFloat() % size.width,
                                y = (i * 260).toFloat() % size.height
                            )
                        )
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp, vertical = 36.dp)
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                // Logo y saludo
                Icon(
                    imageVector = Icons.Default.Pets,
                    contentDescription = "Logo Pepets Spa",
                    tint = Color(0xFF0288D1),
                    modifier = Modifier
                        .size(85.dp)
                        .scale(scale)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "¡Hola, $userName! 👋",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0288D1),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "¿Qué deseas hacer hoy?",
                    fontSize = 16.sp,
                    color = Color(0xFF4F4F4F),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Tarjeta con opciones
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
                        modifier = Modifier
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "Menú Principal",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0288D1)
                        )

                        Divider(thickness = 1.dp, color = Color(0xFFB3E5FC))

                        HomeButton("Ver Mascotas", Icons.Default.Pets, listOf(Color(0xFF81D4FA), Color(0xFF4FC3F7))) {
                            navController.navigate("pets")
                        }

                        HomeButton("Servicios", Icons.Default.MiscellaneousServices, listOf(Color(0xFF4FC3F7), Color(0xFF29B6F6))) {
                            navController.navigate("services")
                        }

                        HomeButton("Citas", Icons.Default.Event, listOf(Color(0xFF29B6F6), Color(0xFF0288D1))) {
                            navController.navigate("appointments")
                        }

                        HomeButton("Perfil", Icons.Default.Person, listOf(Color(0xFF0288D1), Color(0xFF0277BD))) {
                            navController.navigate("profile")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    gradient: List<Color>,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
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
                    Brush.horizontalGradient(gradient),
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
