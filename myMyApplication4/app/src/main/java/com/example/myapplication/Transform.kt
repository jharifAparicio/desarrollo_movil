package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.*
import androidx.core.graphics.withTranslation

class Transform@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint()
    private val TAG: String = "Grafico"
    private val path = Path()

    // Limites de coordenadas
    private val limInfX: Float = -20f
    private val limSupX: Float = 20f
    private val limInfY: Float = -20f
    private val limSupY: Float = 20f

    // Triángulo original en coordenadas homogéneas
    private val originalTriangle = arrayOf(
        floatArrayOf(0f, -50f, 10f),
        floatArrayOf(-50f, 50f, 10f),
        floatArrayOf(50f, 50f, 10f)
    )

    // Matriz de transformación acumulada
    private var transformationMatrix = identityMatrix()

    // Función para crear una matriz identidad (sin transformación)
    private fun identityMatrix(): Array<FloatArray> {
        return arrayOf(
            floatArrayOf(1f, 0f, 0f),
            floatArrayOf(0f, 1f, 0f),
            floatArrayOf(0f, 0f, 1f)
        )
    }

    // Función para multiplicar matrices
    private fun multiplyMatrices(a: Array<FloatArray>, b: Array<FloatArray>): Array<FloatArray> {
        val result = Array(3) { FloatArray(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                result[i][j] = 0f
                for (k in 0..2) {
                    result[i][j] += a[i][k] * b[k][j] // Reemplazamos sumOf por un bucle tradicional
                }
            }
        }
        return result
    }

    // Función para multiplicar una matriz por un punto
    private fun multiplyMatrixAndPoint(m: Array<FloatArray>, p: FloatArray): FloatArray {
        val result = FloatArray(3)
        for (i in 0..2) {
            result[i] = 0f
            for (j in 0..2) {
                result[i] += m[i][j] * p[j] // Reemplazamos sumOf por un bucle tradicional
            }
        }
        return result
    }

    // Función de rotación
    fun rotate(angleDegrees: Float) {
        val angleRadians = Math.toRadians(angleDegrees.toDouble()).toFloat()
        val cosA = cos(angleRadians.toDouble()).toFloat()
        val sinA = sin(angleRadians.toDouble()).toFloat()

        val rotationMatrix = arrayOf(
            floatArrayOf(cosA, -sinA, 0f),
            floatArrayOf(sinA, cosA, 0f),
            floatArrayOf(0f, 0f, 1f)
        )

        transformationMatrix = multiplyMatrices(rotationMatrix, transformationMatrix)
        invalidate() // Redibujar
    }

    // Función de zoom
    fun zoom(scaleX: Float, scaleY: Float) {
        val scaleMatrix = arrayOf(
            floatArrayOf(scaleX, 0f, 0f),
            floatArrayOf(0f, scaleY, 0f),
            floatArrayOf(0f, 0f, 1f)
        )

        transformationMatrix = multiplyMatrices(scaleMatrix, transformationMatrix)
        invalidate() // Redibujar
    }

    // Función de traslación
    fun translate(dx: Float, dy: Float) {
        val translationMatrix = arrayOf(
            floatArrayOf(1f, 0f, dx),
            floatArrayOf(0f, 1f, dy),
            floatArrayOf(0f, 0f, 1f)
        )

        transformationMatrix = multiplyMatrices(translationMatrix, transformationMatrix)
        invalidate() // Redibujar
    }

    // Función para dibujar el triángulo transformado
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.RED
        paint.strokeWidth = 3f

        val ancho = width
        val alto = height
        Log.d(TAG, "ancho: $ancho, alto: $alto")

        // Guardamos el estado actual del Canvas
        canvas.withTranslation(ancho / 2f, alto / 2f) {

            // Realizamos las transformaciones
            paint.color = Color.GRAY
            paint.strokeWidth = 2f

            // Dibujamos los ejes
            drawLine(-ancho / 2f, 0f, ancho / 2f, 0f, paint) // Eje X
            drawLine(0f, -alto / 2f, 0f, alto / 2f, paint) // Eje Y

            // Limpiar el Path antes de dibujar
            path.reset()  // Limpiar el Path para evitar trazos antiguos

            // Dibujar el triángulo
            val transformedPoints =
                originalTriangle.map { multiplyMatrixAndPoint(transformationMatrix, it) }

            path.moveTo(transformedPoints[0][0], transformedPoints[0][1])
            path.lineTo(transformedPoints[1][0], transformedPoints[1][1])
            path.lineTo(transformedPoints[2][0], transformedPoints[2][1])
            path.close()

            // Dibujar el triángulo en el Canvas
            paint.color = Color.RED
            drawPath(path, paint)

            // Restaurar el estado original del Canvas
        }
    }
}