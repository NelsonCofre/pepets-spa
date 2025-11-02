package com.example.pepets_spa.screens

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pepets_spa.navigation.BottomNavScreen
import com.example.pepets_spa.viewmodel.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, usuarioViewModel: UsuarioViewModel, userName: String = "Usuario") {

    val usuarioId = usuarioViewModel.usuarioLogeado.value?.id ?: 0

    val bottomNavItems = listOf(
        BottomNavScreen.Pets,
        BottomNavScreen.Services,
        BottomNavScreen.Appointments,
        BottomNavScreen.Profile
    )

    var startAnimation by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (startAnimation) 1f else 0.8f)
    LaunchedEffect(Unit) { startAnimation = true }

    var selectedItem by remember { mutableStateOf<BottomNavScreen>(BottomNavScreen.Pets) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF0288D1), Color(0xFF4FC3F7))
                        )
                    )
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Pepets Spa",
                            fontSize = 26.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                            color = Color.White
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
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    modifier = Modifier.shadow(8.dp, RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0288D1),
                tonalElevation = 8.dp
            ) {
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title, tint = if (screen == selectedItem) Color.White else Color(0xFFB3E5FC)) },
                        label = { Text(screen.title, color = if (screen == selectedItem) Color.White else Color(0xFFB3E5FC)) },
                        selected = screen == selectedItem,
                        onClick = { selectedItem = screen }
                    )
                }
            }
        },
        containerColor = Color(0xFFE3F2FD)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFB3E5FC), Color(0xFFFFFFFF))
                    )
                )
        ) {
            when (selectedItem) {
                BottomNavScreen.Pets -> PetsScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel  // ✅ se pasa el ViewModel
                )
                BottomNavScreen.Services -> ServicesScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel // ✅ se pasa el ViewModel
                )
                BottomNavScreen.Appointments -> AppointmentScreen(
                    navController = navController,
                    usuarioId = usuarioId
                )
                BottomNavScreen.Profile -> ProfileScreen(
                    navController = navController,
                    userName = userName
                )
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
                Icon(icon, contentDescription = text, tint = Color.White)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text, color = Color.White, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 17.sp)
            }
        }
    }
}
