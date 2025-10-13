package com.example.pepets_spa.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pepets_spa.model.*
import com.example.pepets_spa.repository.dao.*

@Database(
    entities = [
        Usuario::class,
        Mascota::class,
        Servicio::class,
        Cita::class,
        ServicioMascota::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun mascotaDao(): MascotaDao
    abstract fun servicioDao(): ServicioDao
    abstract fun citaDao(): CitaDao
    abstract fun servicioMascotaDao(): ServicioMascotaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pepets_spa.db"
                )
                    .fallbackToDestructiveMigration() // elimina datos si cambia la versión
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
