package com.example.testdb2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOLugar {
    // suspend en segundo plano
    @Query("SELECT * FROM lugares")
    suspend fun obtenerLugares (): MutableList<Lugar>

    @Query("SELECT * FROM lugares WHERE id = :id")
    suspend fun obtenerLugar (id: Int): Lugar

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarLugar(lugar: Lugar)
    @Query("UPDATE lugares SET nombre= :nombre, descripcion= :descripcion, latitud= :latitud, longitud= :longitud WHERE id = :id")
    suspend fun actualizarLugar(id: Int, nombre: String, descripcion: String, latitud: Double, longitud: Double)

    @Query("DELETE FROM lugares WHERE id = :id")
    suspend fun borrarLugar(id: Int)
}