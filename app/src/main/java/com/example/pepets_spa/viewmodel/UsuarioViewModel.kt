package com.example.pepets_spa.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pepets_spa.model.Usuario
import com.example.pepets_spa.repository.AppDatabase
import com.example.pepets_spa.repository.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioRepository: UsuarioRepository
    val usuarios: LiveData<List<Usuario>>

    // 🔹 Usuario actualmente logeado
    val usuarioLogeado: MutableState<Usuario?> = mutableStateOf(null)

    init {
        val usuarioDao = AppDatabase.getDatabase(application).usuarioDao()
        usuarioRepository = UsuarioRepository(usuarioDao)
        usuarios = usuarioRepository.obtenerUsuarios()
    }

    fun insertar(usuario: Usuario) = viewModelScope.launch {
        usuarioRepository.insertar(usuario)
    }

    fun actualizar(usuario: Usuario) = viewModelScope.launch {
        usuarioRepository.actualizar(usuario)
    }

    fun eliminar(usuario: Usuario) = viewModelScope.launch {
        usuarioRepository.eliminar(usuario)
    }

    // 🔹 Validar login y guardar usuario logeado
    fun validarLogin(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = usuarioRepository.obtenerUsuarioPorEmail(email)
            if (user != null && user.password == password) {
                usuarioLogeado.value = user  // ✅ Guardamos el usuario logeado
                callback(true)
            } else {
                callback(false)
            }
        }
    }

    fun logout() {
        usuarioLogeado.value = null
    }

    fun existeUsuario(email: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val usuario = usuarioRepository.obtenerUsuarioPorEmail(email)
            onResult(usuario != null)
        }
    }
}
