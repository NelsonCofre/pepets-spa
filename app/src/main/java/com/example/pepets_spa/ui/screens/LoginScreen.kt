package com.example.pepets_spa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepets_spa.ui.viewmodel.AppViewModel
import com.example.pepets_spa.ui.navigation.Routes

@Composable
fun LoginScreen(navController: NavHostController, vm: AppViewModel) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()

    // If already logged -> go home
    val user = vm.usuario.collectAsState().value
    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(padding),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Pepets Spa", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    vm.login(email, pass) {
                        /* onSuccess handled in LaunchedEffect observing vm.usuario */
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !loading
            ) {
                Text("Iniciar sesión")
            }

            Spacer(Modifier.height(8.dp))

            TextButton(onClick = { navController.navigate(Routes.REGISTER) }) {
                Text("¿No tienes cuenta? Regístrate")
            }

            if (loading) {
                Spacer(Modifier.height(12.dp))
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            error?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
