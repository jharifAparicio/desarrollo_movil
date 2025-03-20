package com.example.myapplication3

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BaseDatosActivity : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_base_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = BaseDatos(this)
        dbHelper = db.writableDatabase

        val buttonMapa: Button = findViewById(R.id.buttonMapa)
        val editTextId: EditText = findViewById(R.id.editTextId)
        val editTextNombre: EditText = findViewById(R.id.editTextNombre)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion)
        val editTextLatitud: EditText = findViewById(R.id.editTextLatitud)
        val editTextLongitud: EditText = findViewById(R.id.editTextLongitud)
        //botones de accion
        val buttonNuevo: Button = findViewById(R.id.buttonNuevo)
        val buttonListar: Button = findViewById(R.id.buttonListar)
        val buttonActualizar: Button = findViewById(R.id.buttonActualizar)
        val buttonBorrar: Button = findViewById(R.id.buttonBorrar)

        // CRUD
        // Create
        buttonNuevo.setOnClickListener {
            val id = editTextId.text.toString().toInt()
            val nombre = editTextNombre.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()
            val latitud = editTextLatitud.text.toString().toFloat()
            val longitud = editTextLongitud.text.toString().toFloat()

            try {
                // Ejecutar la consulta SQL
                dbHelper.execSQL(
                    "INSERT INTO lugares (nombre, descripcion, latitud, longitud) " +
                            "VALUES ($nombre, $descripcion, $latitud, $longitud)"
                )

                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                // borrar los campos
                editTextId.text.clear()
                editTextNombre.text.clear()
                editTextDescripcion.text.clear()
                editTextLatitud.text.clear()
                editTextLongitud.text.clear()

            } catch (e: Exception) {
                // Capturar cualquier error y mostrarlo en el LogCat
                Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
                // borrar los campos
                editTextId.text.clear()
                editTextNombre.text.clear()
                editTextDescripcion.text.clear()
                editTextLatitud.text.clear()
                editTextLongitud.text.clear()

                e.printStackTrace()
            }
        }

        buttonActualizar.setOnClickListener{
            val id = editTextId.text.toString().toInt()
            val nombre = editTextNombre.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()
            val latitud = editTextLatitud.text.toString().toFloat()
            val longitud = editTextLongitud.text.toString().toFloat()
            // 
        }

        // mapa
        buttonMapa.setOnClickListener {
            val latitud = editTextLatitud.text.toString().toDouble()
            val longitud = editTextLongitud.text.toString().toDouble()
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra("latitud", latitud)
                putExtra("longitud", longitud)
                putExtra("nombre", nombre)
                putExtra("descripcion", descripcion)
            }

            startActivity(intent)
        }
    }
}

