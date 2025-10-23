package com.example.pepets_spa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.pepets_spa.navigation.AppNavigation
import com.example.pepets_spa.viewmodel.UsuarioViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val usuarioViewModel: UsuarioViewModel by viewModels()

            AppNavigation(navController = navController, usuarioViewModel = usuarioViewModel)
        }
    }
}
