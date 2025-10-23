package com.example.pepets_spa.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pepets_spa.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // Estado para activar animaciones
    var startAnimation by remember { mutableStateOf(false) }

    // Animaciones usando animateFloatAsState para estabilidad
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = { OvershootInterpolator(3f).getInterpolation(it) }
        )
    )

    val rotation by animateFloatAsState(
        targetValue = if (startAnimation) 360f else 0f,
        animationSpec = tween(durationMillis = 1200)
    )

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1200)
    )

    // Lanzar animación al montar la pantalla
    LaunchedEffect(true) {
        startAnimation = true
        delay(2000) // Tiempo total antes de ir a login
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // Pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF80CBC4), Color(0xFF00695C))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            /*
            // Logo animado (asegúrate de tener R.drawable.logo)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Pepets Spa",
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale)
                    .rotate(rotation)
            )
            
             */

            Spacer(modifier = Modifier.height(16.dp))

            // Texto elegante
            Text(
                text = "Pepets Spa",
                fontSize = 32.sp,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.alpha(alpha)
            )
        }
    }
}
