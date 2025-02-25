package com.example.myapplication3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

class Animacion2(context: Context): View(context){
    private val paint = Paint()
    private var xActual: Float = -20f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val ancho = width
        val alto = height

        // definimos limites
        val limInfX:Float = -20f
        val limSupX = 20f
        val limInfY:Float = -20f
        val limSupY = 20f

        // dibujamos las lineas guias
        // x es horizontal y y es vertical
        paint.color = Color.RED
        paint.strokeWidth = 3f
        canvas.drawLine(0f,alto/2f,ancho.toFloat(),alto/2f,paint);
        canvas.drawLine(ancho/2f,alto.toFloat(),ancho/2f,0f,paint);

        paint.color = Color.BLACK
        var x = limInfX
        while(x <= limSupX){
            var y = f(x)
            // transformar x y a coordenadas del canvas
            var xt = ((x-limInfX)/(limSupX-limInfX))*ancho
            var yt = ((y-limInfY)/(limSupY-limInfY))*alto
            // dibujar el punto
            canvas.drawCircle(xt,alto-yt,3f,paint)

            x+=0.01f
        }

        paint.color = Color.BLUE
        val yActual = f(xActual)
        val xtActual = ((xActual - limInfX) / (limSupX - limInfX)) * ancho
        val ytActual = ((yActual - limInfY) / (limSupY - limInfY)) * alto
        canvas.drawCircle(xtActual, alto - ytActual, 40f, paint)

        // Mover el círculo
        xActual += 0.2f
        if (xActual > limSupX) {
            xActual = limInfX  // Reiniciar al inicio
        }

        // Redibujar con retraso
        postInvalidateDelayed(40)
    }
    private fun f(x:Float): Float{
        return  sin(x)
    }

}