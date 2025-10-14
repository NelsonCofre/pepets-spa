package com.example.pepets_spa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pepets_spa.ui.screens.*
import com.example.pepets_spa.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val usuarioViewModel: UsuarioViewModel by viewModels()

            NavHost(navController = navController, startDestination = "register") {

                composable("login") {
                    LoginScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                }

                composable("register") {
                    RegisterScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                }

                composable("home") {
                    HomeScreen(navController = navController)
                }
            }
        }
    }
}
