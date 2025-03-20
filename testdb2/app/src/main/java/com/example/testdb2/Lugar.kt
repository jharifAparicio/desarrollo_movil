package com.example.testdb2

import androidx.room.Entity
import androidx.room.PrimaryKey

// crea la tabla de forma implicita
@Entity(tableName = "lugares")

data class Lugar(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String = "",
    val descripcion: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
)
