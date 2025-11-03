    package com.example.pepets_spa.navigation

    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.navigation.NavHostController
    import androidx.navigation.NavType
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.navArgument
    import com.example.pepets_spa.screens.*
    import com.example.pepets_spa.viewmodel.UsuarioViewModel



    @Composable
    fun AppNavigation(
        navController: NavHostController,
        usuarioViewModel: UsuarioViewModel
    ) {
        val usuarioLogeado by usuarioViewModel.usuarioLogeado

        NavHost(navController = navController, startDestination = "login") {

            composable("login") {
                LoginScreen(navController, usuarioViewModel)
            }

            composable("register") {
                RegisterScreen(navController, usuarioViewModel)
            }

            composable("home") {
                HomeScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel
                )
            }



            composable("pets") {
                PetsScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel
                )
            }


            composable("profile") {
                ProfileScreen(navController = navController)
            }


            composable("services") {
                ServicesScreen(navController, usuarioViewModel = usuarioViewModel)
            }

            // Pantalla para agendar cita
            composable(
                route = "agendarCita/{servicioId}/{usuarioId}",
                arguments = listOf(
                    navArgument("servicioId") { type = NavType.IntType },
                    navArgument("usuarioId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val servicioId = backStackEntry.arguments?.getInt("servicioId") ?: 0
                val usuarioId = backStackEntry.arguments?.getInt("usuarioId") ?: 0
                AgendarCitaScreen(
                    navController = navController,
                    servicioId = servicioId,
                    usuarioId = usuarioId
                )
            }

            // Pantalla de historial de citas
            composable("citas") {
                val usuarioId = usuarioLogeado?.id ?: 0
                AppointmentScreen(
                    navController = navController,
                    usuarioId = usuarioId
                )
            }
            composable("edit_profile") { EditProfileScreen(navController, usuarioViewModel) }
            composable("change_password") { ChangePasswordScreen(navController, usuarioViewModel) }

        }
    }
