package com.example.pepets_spa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.pepets_spa.ui.navigation.AppNavigation
import com.example.pepets_spa.ui.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {

    private val vm: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nav = rememberNavController()
            AppNavigation(navController = nav, vm = vm)
        }
    }
}
