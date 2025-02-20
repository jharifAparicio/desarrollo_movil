package com.example.myapplication3

import android.annotation.SuppressLint
import android.media.VolumeShaper.Operation
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculadoraActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numero:Int = 0;
        var numero1:Double = 0.0;
        val numero2:Double = 0.0;
        val resultado:Double = 0.0;

        val Values:TextView = findViewById(R.id.values)
        val Borrar:Button = findViewById(R.id.AC)

        // operaciones
        val Operations = listOf(
            findViewById<Button>(R.id.suma),
            findViewById<Button>(R.id.resta),
            findViewById<Button>(R.id.Multiplicacion),
            findViewById<Button>(R.id.division),
            findViewById<Button>(R.id.igual)
        )

        val numButtons = listOf(
            findViewById<Button>(R.id.num0),
            findViewById<Button>(R.id.num1),
            findViewById<Button>(R.id.num2),
            findViewById<Button>(R.id.num3),
            findViewById<Button>(R.id.num4),
            findViewById<Button>(R.id.num5),
            findViewById<Button>(R.id.num6),
            findViewById<Button>(R.id.num7),
            findViewById<Button>(R.id.num8),
            findViewById<Button>(R.id.num9)
        )


        numButtons.forEach { button ->
            button.setOnClickListener {
                Values.text = Values.text.toString() + button.text.toString()
            }
        }

        Operations.forEach{ operaciones ->
            operaciones.setOnClickListener{
                if(operaciones.text != "+"){
                    numero1 = Values.text.toString().toDouble()
                    Values.text = ""

                }
            }
        }


        Borrar.setOnClickListener {
            Values.text = ""
        }

    }
}









