package com.example.pepets_spa.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.pepets_spa.model.Mascota
import com.example.pepets_spa.repository.AppDatabase
import com.example.pepets_spa.repository.MascotaRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MascotaViewModel(application: Application) : AndroidViewModel(application) {

    private val mascotaRepository: MascotaRepository
    val mascotas: LiveData<List<Mascota>>

    init {
        val mascotaDao = AppDatabase.getDatabase(application).mascotaDao()
        mascotaRepository = MascotaRepository(mascotaDao)
        mascotas = mascotaRepository.obtenerMascotas().asLiveData()
    }

    fun insertar(mascota: Mascota) = viewModelScope.launch {
        mascotaRepository.insertar(mascota)
    }

    fun actualizar(mascota: Mascota) = viewModelScope.launch {
        mascotaRepository.actualizar(mascota)
    }

    fun eliminar(mascota: Mascota) = viewModelScope.launch {
        mascotaRepository.eliminar(mascota)
    }

    fun obtenerMascotaPorId(id: Int): LiveData<Mascota?> {
        return mascotaRepository.obtenerMascotaPorId(id).asLiveData()
    }
}
