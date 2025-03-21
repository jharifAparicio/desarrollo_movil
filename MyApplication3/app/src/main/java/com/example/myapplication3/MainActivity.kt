package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val BtnBaseDatos:Button = findViewById(R.id.BtnBaseDatos)
        val BtnGraficos:Button = findViewById(R.id.BtnGraficos)
        val BtnAnimacion:Button = findViewById(R.id.BtnAnimacion)
        val BtnRecursividad: Button = findViewById(R.id.BtnRecursividad)
        val BtnCalculadora:Button = findViewById(R.id.BtnCalculadora)
        val BtnSalir:Button = findViewById(R.id.BtnSalir)

        BtnBaseDatos.setOnClickListener{
            val intent =Intent(this, BaseDatosActivity::class.java)
            startActivity(intent)
        }

        BtnGraficos.setOnClickListener{
            val intent =Intent(this, GraficosActivity::class.java)
            startActivity(intent)
        }

        BtnAnimacion.setOnClickListener{
            val intent =Intent(this, AnimacionActivity::class.java)
            startActivity(intent)
        }

        BtnCalculadora.setOnClickListener{
            val intent =Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        BtnRecursividad.setOnClickListener{
            val intent =Intent(this, RecursividadActivity::class.java)
            startActivity(intent)
        }

        BtnSalir.setOnClickListener {
            finish()
        }
    }
}