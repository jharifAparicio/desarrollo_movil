package com.example.myapplication3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context): SQLiteOpenHelper(context, BASE_DE_DATOS, null,  VERSION_DB){

    companion object{
        private val BASE_DE_DATOS: String = "bdsis104"
        private val VERSION_DB: Int = 1
        private val TABLE_NAME: String = "lugares"

        // columnas de la base de datos
        private val ID: String = "id"
        private val NAME: String = "nombre"
        private val DESCRIPTION: String = "descripcion"
        private val LATITUDE: String = "latitud"
        private val LONGITUDE: String = "longitud"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_NAME " +
                "($ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NAME TEXT, " +
                "$DESCRIPTION TEXT, " +
                "$LATITUDE REAL, " +
                "$LONGITUDE REAL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase? , oldVersion: Int, newVersion: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }
}