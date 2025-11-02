package com.example.pepets_spa.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.pepets_spa.model.Cita
import com.example.pepets_spa.repository.AppDatabase
import com.example.pepets_spa.repository.CitaRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CitaViewModel(application: Application) : AndroidViewModel(application) {

    private val citaRepository: CitaRepository
    val citas: LiveData<List<Cita>>

    init {
        val citaDao = AppDatabase.getDatabase(application).citaDao()
        citaRepository = CitaRepository(citaDao)
        citas = citaRepository.obtenerCitas().asLiveData()
    }

    fun insertar(cita: Cita) = viewModelScope.launch {
        citaRepository.insertar(cita)
    }

    fun actualizar(cita: Cita) = viewModelScope.launch {
        citaRepository.actualizar(cita)
    }

    fun eliminar(cita: Cita) = viewModelScope.launch {
        citaRepository.eliminar(cita)
    }

    fun obtenerCitaPorId(id: Int): LiveData<Cita?> {
        return citaRepository.obtenerCitaPorId(id).asLiveData()
    }

    fun obtenerCitasDeUsuario(usuarioId: Int): LiveData<List<Cita>> {
        return citaRepository.obtenerCitasDeUsuario(usuarioId).asLiveData()
    }

}
