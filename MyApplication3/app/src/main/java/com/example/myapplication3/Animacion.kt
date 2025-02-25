package com.example.myapplication3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlin.random.Random

class Animacion (contex:Context): View(contex){
    private val paint = Paint()
    private var x: Float = Random.nextFloat() * 500f
    private var y: Float = Random.nextFloat() * 500f
    private var r: Float = Random.nextFloat() * 50f + 15f
    private var dx: Float = Random.nextFloat() * 10f + 5f
    private var dy: Float = Random.nextFloat() * 10f + 5f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.BLUE
        canvas.drawCircle(x + r, y + r, r, paint)

        if (x + r * 2 >= width) {
            r = Random.nextFloat() * 50f + 15f
            x = width.toFloat() - r * 2 -1
            dx *= -1
        }

        if (x <= 0) {
            dx *= -1
            x = 1f
        }
        if (y <= 0 || y + r * 2 >= height) {
            dy *= -1
        }

        x += dx
        y += dy

        postInvalidateDelayed(15)
    }
}