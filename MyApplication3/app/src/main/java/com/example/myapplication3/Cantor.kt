package com.example.myapplication3

import android.R.integer
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class Cantor(context: Context): View(context) {
    private val paint = Paint()
    // private val
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.strokeWidth = 3f
        val ancho = canvas.width
        val inicioX = 0.0f
        val finX = ancho.toFloat()
        val inicioY = 100.0f
        val nivel = 5

    }

    private fun dibujarCantor (canvas: Canvas,inicioX: Float, finX: Float, Y: Float, nivel: Int){
        if(nivel == 0) return
        // dibujamos la linea completa
        canvas.drawLine(inicioX,Y,finX,Y,paint)
        val segmento = (inicioX-finX) / 3
        val nuevoY = Y + 50.0f
        dibujarCantor(canvas, inicioX, inicioX + segmento, nuevoY, nivel - 1)
        dibujarCantor(canvas, finX - segmento, finX, nuevoY, nivel - 1)
    }
}