package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var grafico: Transform
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grafico = findViewById(R.id.graficoView)
        //val grafico = Transform(this)

        findViewById<Button>(R.id.rotateButtonLeft).setOnClickListener {
            grafico.rotate(+10f)
        }

        findViewById<Button>(R.id.rotateButtonRigth).setOnClickListener {
            grafico.rotate(-10f)
        }

        findViewById<Button>(R.id.zoomButtonIn).setOnClickListener {
            grafico.zoom(1.1f, 1.1f)
        }

        findViewById<Button>(R.id.zoomButtonOut).setOnClickListener {
            grafico.zoom(-1.1f, -1.1f)
        }

        findViewById<Button>(R.id.translateButton).setOnClickListener {
            grafico.translate(10f, 10f)
        }
    }
}