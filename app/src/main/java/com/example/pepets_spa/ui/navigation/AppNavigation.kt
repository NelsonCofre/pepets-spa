package com.example.pepets_spa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pepets_spa.ui.screens.*
import com.example.pepets_spa.ui.viewmodel.AppViewModel

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val MASCOTAS = "mascotas"
    const val SERVICIOS = "servicios"
    const val CITAS = "citas"
    const val PERFIL = "perfil"
    const val CREAR_CITA = "crear_cita"
}

@Composable
fun AppNavigation(navController: NavHostController, vm: AppViewModel) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {

        composable(Routes.LOGIN) {
            LoginScreen(navController = navController, vm = vm)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController = navController, vm = vm)
        }

        composable(Routes.HOME) {
            HomeScreen(navController = navController, vm = vm)
        }

        composable(Routes.MASCOTAS) {
            MascotasScreen(navController = navController, vm = vm)
        }

        composable(Routes.SERVICIOS) {
            ServiciosScreen(navController = navController, vm = vm)
        }

        composable(Routes.CITAS) {
            CitasScreen(navController = navController, vm = vm)
        }

        composable(Routes.PERFIL) {
            PerfilScreen(navController = navController, vm = vm)
        }

        composable(Routes.CREAR_CITA) {
            CrearCitaScreen(navController = navController, vm = vm)
        }
    }
}
