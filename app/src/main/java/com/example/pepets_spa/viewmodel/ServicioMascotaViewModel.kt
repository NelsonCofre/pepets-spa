package com.example.pepets_spa.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pepets_spa.model.ServicioMascota
import com.example.pepets_spa.repository.AppDatabase
import com.example.pepets_spa.repository.ServicioMascotaRepository
import kotlinx.coroutines.launch

class ServicioMascotaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ServicioMascotaRepository

    init {
        val dao = AppDatabase.getDatabase(application).servicioMascotaDao()
        repository = ServicioMascotaRepository(dao)
    }

    fun insertar(relacion: ServicioMascota) = viewModelScope.launch {
        repository.insertar(relacion)
    }

    fun actualizar(relacion: ServicioMascota) = viewModelScope.launch {
        repository.actualizar(relacion)
    }

    fun eliminar(relacion: ServicioMascota) = viewModelScope.launch {
        repository.eliminar(relacion)
    }

    fun obtenerServiciosDeMascota(mascotaId: Int) =
        repository.obtenerServiciosDeMascota(mascotaId).asLiveData()

    fun obtenerMascotasDeServicio(servicioId: Int) =
        repository.obtenerMascotasDeServicio(servicioId).asLiveData()

    fun obtenerPorId(id: Int) =
        repository.obtenerPorId(id).asLiveData()
}
