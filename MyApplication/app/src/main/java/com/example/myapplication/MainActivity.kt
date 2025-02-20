package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        //val no cambia de direccion, var cambia de direccion de memoria
        val editTextValueA: EditText = findViewById(R.id.editTextValueA)
        val editTextValueB: EditText = findViewById(R.id.editTextValueB)
        val editTextValueC: EditText = findViewById(R.id.editTextValueC)
        val editTextValueD: EditText = findViewById(R.id.editTextValueD)
        val buttonOperations: Button = findViewById(R.id.buttonOperations)
        val textViewResult: TextView = findViewById(R.id.textViewResult)
        // int a = 10; float x = parseInt(a); -> conversion explicita

        buttonOperations.setOnClickListener { // esto es implicito
            val a: Int = editTextValueA.text.toString().toInt()
            val b: Int = editTextValueB.text.toString().toInt()
            val c: Int = editTextValueC.text.toString().toInt()
            val d: Int = editTextValueD.text.toString().toInt()
            val operation = Operation()
            val add = operation.add(a,b,c,d)
            val multiply = operation.multiply(a,b,c,d)
            val subtract = operation.subtract(a,b,c,d)
            val split = operation.split(a,b,c,d)
            val result: String =
                    " suma: "+ add +
                    "\n resta: "+ subtract +
                    "\n multiplicacion: "+ multiply +
                    "\n division: "+ split

            textViewResult.text =  result
        }

    }
}