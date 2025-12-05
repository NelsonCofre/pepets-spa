package com.example.pepets_spa.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pepets_spa.data.model.*
import com.example.pepets_spa.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    private val repo = AppRepository()

    private val _usuario = MutableStateFlow<Cliente?>(null)
    val usuario: StateFlow<Cliente?> = _usuario

    private val _mascotas = MutableStateFlow<List<Mascota>>(emptyList())
    val mascotas: StateFlow<List<Mascota>> = _mascotas

    private val _servicios = MutableStateFlow<List<Servicio>>(emptyList())
    val servicios: StateFlow<List<Servicio>> = _servicios

    private val _citas = MutableStateFlow<List<Cita>>(emptyList())
    val citas: StateFlow<List<Cita>> = _citas

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    // -------------------------
    // LOGIN
    // -------------------------
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loading.value = true
            val res = repo.login(email, password)
            if (res.isSuccessful) {
                _usuario.value = res.body()
                onSuccess()
            } else {
                _error.value = "Email o contraseña incorrectos"
            }
            _loading.value = false
        }
    }


    // -------------------------
    // REGISTER
    // -------------------------
    fun register(nombre: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loading.value = true
            val res = repo.register(nombre, email, password)
            if (res.isSuccessful) {
                _usuario.value = res.body()
                onSuccess()
            } else {
                _error.value = "No se pudo registrar"
            }
            _loading.value = false
        }
    }


    // -------------------------
    // MASCOTAS
    // -------------------------
    fun cargarMascotas() {
        val id = usuario.value?.id ?: return
        viewModelScope.launch {
            val res = repo.getMascotas(id)
            if (res.isSuccessful) {
                _mascotas.value = res.body() ?: emptyList()
            }
        }
    }

    fun agregarMascota(nombre: String, tipo: String, edad: Int) {
        val id = usuario.value?.id ?: return
        viewModelScope.launch {
            val mascota = Mascota(null, id, nombre, tipo, edad)
            val res = repo.crearMascota(mascota)
            if (res.isSuccessful) cargarMascotas()
        }
    }

    fun eliminarMascota(id: Long) {
        viewModelScope.launch {
            repo.eliminarMascota(id)
            cargarMascotas()
        }
    }


    // -------------------------
    // SERVICIOS
    // -------------------------
    fun cargarServicios() {
        viewModelScope.launch {
            val res = repo.getServicios()
            if (res.isSuccessful) {
                _servicios.value = res.body() ?: emptyList()
            }
        }
    }


    // -------------------------
    // CITAS
    // -------------------------
    fun crearCita(servicioId: Long, fecha: String, hora: String) {
        val clienteId = usuario.value?.id ?: return
        val mascotaId = mascotas.value.firstOrNull()?.id ?: return

        viewModelScope.launch {
            val cita = Cita(
                id = null,
                clienteId = clienteId,
                mascotaId = mascotaId,
                servicioId = servicioId,
                fecha = fecha,
                hora = hora,
                estado = "pendiente"
            )
            repo.crearCita(cita)
            cargarCitas()
        }
    }

    fun cargarCitas() {
        val id = usuario.value?.id ?: return
        viewModelScope.launch {
            val res = repo.getCitas(id)
            if (res.isSuccessful) {
                _citas.value = res.body() ?: emptyList()
            }
        }
    }


    // -------------------------
    // LOGOUT
    // -------------------------
    fun logout() {
        _usuario.value = null
        _mascotas.value = emptyList()
        _servicios.value = emptyList()
        _citas.value = emptyList()
    }
}
