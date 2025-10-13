package com.example.pepets_spa.viewmodel

import android.app.Application
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

    // 🔹 Ya no devuelve LiveData, sino suspend en Repository
    fun validarLogin(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = usuarioRepository.obtenerUsuarioPorEmail(email)
            callback(user != null && user.password == password)
        }
    }
}
