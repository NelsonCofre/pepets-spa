package com.example.pepets_spa.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pepets_spa.R
import com.example.pepets_spa.viewmodel.UsuarioViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val usuario = usuarioViewModel.usuarioLogeado.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F9FF))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.ic_user_placeholder),
            contentDescription = "Foto de perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre y correo
        Text(
            text = usuario?.nombre ?: "Usuario desconocido",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0288D1)
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = usuario?.email ?: "correo@desconocido.com",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Opciones del perfil
        ProfileOption(
            icon = Icons.Default.Edit,
            title = "Editar perfil",
            color = Color(0xFF64B5F6)
        ) {
            navController.navigate("edit_profile")
        }

        Spacer(modifier = Modifier.height(12.dp))

        ProfileOption(
            icon = Icons.Default.Lock,
            title = "Cambiar contraseña",
            color = Color(0xFFFFB74D)
        ) {
            navController.navigate("change_password")
        }

        Spacer(modifier = Modifier.height(12.dp))

        ProfileOption(
            icon = Icons.Default.ExitToApp,
            title = "Cerrar sesión",
            color = Color(0xFFE57373)
        ) {
            usuarioViewModel.logout()
            navController.navigate("login") {
                popUpTo("profile") { inclusive = true }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Pepets Spa © 2025",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ProfileOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333)
                )
            )
        }
    }
}
