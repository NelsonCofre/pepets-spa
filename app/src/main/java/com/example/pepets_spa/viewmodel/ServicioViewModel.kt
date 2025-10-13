package com.example.pepets_spa.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.pepets_spa.model.Servicio
import com.example.pepets_spa.repository.AppDatabase
import com.example.pepets_spa.repository.ServicioRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ServicioViewModel(application: Application) : AndroidViewModel(application) {

    private val servicioRepository: ServicioRepository
    val servicios: LiveData<List<Servicio>>

    init {
        val servicioDao = AppDatabase.getDatabase(application).servicioDao()
        servicioRepository = ServicioRepository(servicioDao)
        servicios = servicioRepository.obtenerServicios().asLiveData()
    }

    fun insertar(servicio: Servicio) = viewModelScope.launch {
        servicioRepository.insertar(servicio)
    }

    fun actualizar(servicio: Servicio) = viewModelScope.launch {
        servicioRepository.actualizar(servicio)
    }

    fun eliminar(servicio: Servicio) = viewModelScope.launch {
        servicioRepository.eliminar(servicio)
    }

    fun obtenerServicioPorId(id: Int): LiveData<Servicio?> {
        return servicioRepository.obtenerServicioPorId(id).asLiveData()
    }
}
