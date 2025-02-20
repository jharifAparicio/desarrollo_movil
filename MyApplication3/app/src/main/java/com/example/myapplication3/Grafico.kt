package com.example.myapplication3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View
import kotlin.math.cos
import kotlin.random.Random

class Grafico (context:Context): View(context){
    private val paint = Paint()
    private val TAG: String = "Grafico"
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.strokeWidth = 3f
        val ancho = canvas.width
        val alto = canvas.height
        Log.d(TAG, "ancho: $ancho, alto: $alto")
        // definimos limites
        val limInfX:Float = -20f
        val limSupX:Float = 20f
        val limInfY:Float = -20f
        val limSupY:Float = 20f
        // dibujamos las lineas guias
        // x es horizontal y y es vertical
        canvas.drawLine(0f,alto/2f,ancho.toFloat(),alto/2f,paint);
        canvas.drawLine(ancho/2f,alto.toFloat(),ancho/2f,0f,paint);

        var x = limInfX
        while(x <= limSupX){
            var y = f(x)
            Log.d(TAG,"X: $x, Y: $y")
            // transformar x y a coordenadas del canvas
            var xt = ((x-limInfX)/(limSupX-limInfX))*ancho
            var yt = ((y-limInfY)/(limSupY-limInfY))*alto
            // dibujar el punto
            canvas.drawCircle(xt,alto-yt,3f,paint)
            x+=0.01f

            if(xt % 1.0 == 0.0){
                canvas.drawLine(xt,alto-yt,xt,alto.toFloat()+5.0f,paint)
            }
            if(yt % 1.0 == 0.0){
                canvas.drawLine(yt,alto-xt,yt,alto.toFloat(),paint)
            }
        }
    }
    private fun f(x:Float): Float{
        return  x*x
    }

    /* override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Dibujar el gráfico aquí
        // color es una propiedad de Paint para determinar el color desde la clase estatica Color
        paint.color = Color.RED
        // strokeWidth es una propiedad de Paint para el grosor
        paint.strokeWidth = 3f
        // drawLine es un método de Canvas para dibujar una línea desde (0,0) hasta (100,100) con
        // el paint definido anteriormente
        canvas.drawLine(0f,0f,1000f,1000f,paint)

        // generar 100 circulos aleatorias en el radio
        for(i in 0..100){
            canvas.drawCircle(Random.nextInt()*ancho.toFloat(),Random.nextInt()*alto.toFloat(),10f,paint)
        }
    }*/
}