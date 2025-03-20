package com.example.testdb2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Lugar::class], version = 1, exportSchema = false)
abstract class BaseDatos: RoomDatabase() {
    abstract fun DAOLugares(): DAOLugar
}