package com.example.testdb2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var room: BaseDatos
    lateinit var lugar: Lugar
    lateinit var listaLugares: MutableList<Lugar>
    //
    lateinit var editTextId: EditText
    lateinit var editTextNombre: EditText
    lateinit var editTextDescripcion: EditText
    lateinit var  editTextLatitud: EditText
    lateinit var editTextLongitud: EditText
    lateinit var viewLugares: TextView

    lateinit var buttonNuevo: Button
    lateinit var buttonListar: Button
    lateinit var buttonActualizar: Button
    lateinit var buttonBorrar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        editTextId = findViewById(R.id.editTextId)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)
        editTextLatitud = findViewById(R.id.editTextLatitud)
        editTextLongitud = findViewById(R.id.editTextLongitud)
        //botones de accion
        buttonNuevo = findViewById(R.id.buttonNuevo)
        buttonListar = findViewById(R.id.buttonListar)
        buttonActualizar = findViewById(R.id.buttonActualizar)
        buttonBorrar = findViewById(R.id.buttonBorrar)

        room = Room.databaseBuilder(this,BaseDatos::class.java, "DBprueba").build()

        // lugar = Lugar(0,"nombre","descripcion",-1.5,2.5)
        // agregarLugar(lugar)

        buttonNuevo.setOnClickListener {
            val nombre: String = editTextNombre.text.toString()
            val descripcion: String = editTextDescripcion.text.toString()
            val latitud: Double = editTextLatitud.text.toString().toDoubleOrNull() ?: 0.0
            val longitud: Double = editTextLongitud.text.toString().toDoubleOrNull() ?: 0.0
            // creamos el objeto para la insercion de datos
            lugar = Lugar(0,nombre,descripcion,latitud,longitud)

            // Log.d("Lugar", "Datos: $lugar")
            agregarLugar(lugar)
        }

        buttonListar.setOnClickListener {
            ListarLugares()
            val texto = listaLugares.joinToString("\n") { lugar ->
                "ID: ${lugar.id}, Nombre: ${lugar.nombre}, Desc: ${lugar.descripcion}, Lat: ${lugar.latitud}, Long: ${lugar.longitud}"
            }

            viewLugares.text = texto.ifEmpty { "No hay lugares registrados" }

        }
    }

    private fun agregarLugar(lugar: Lugar) {
        // permite gestionar las ejecuciones en segundo plano
        lifecycleScope.launch {
            try {
                room.DAOLugares().agregarLugar(lugar)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Lugar agregado correctamente", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error al agregar el lugar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun ListarLugares(){

        lifecycleScope.launch {
            try {
                val lugares = room.DAOLugares().obtenerLugares() // Debe ser una función suspend
                withContext(Dispatchers.Main) {
                    listaLugares.clear()
                    listaLugares.addAll(lugares)
                    // Aquí puedes notificar a un RecyclerView o actualizar la UI
                    Toast.makeText(this@MainActivity, "Lugares cargados", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al obtener lugares",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}