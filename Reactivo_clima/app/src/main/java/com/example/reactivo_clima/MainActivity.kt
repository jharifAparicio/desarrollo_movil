package com.example.reactivo_clima

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    lateinit var textViewLatitud: TextView
    lateinit var textViewLongitud: TextView
    lateinit var textViewTemperatura: TextView
    lateinit var textViewViento: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLatitud = findViewById(R.id.latitud)
        textViewLongitud = findViewById(R.id.longitud)
        textViewTemperatura = findViewById(R.id.temperatura)
        textViewViento = findViewById(R.id.viento)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Verificamos si ya tenemos los permisos
        if (!hasLocationPermission()) {
            requestLocationPermission()
        } else {
            obtenerUbicacion()
        }
    }
    
    private fun obtenerUbicacion() {
        if (hasLocationPermission()) {
            fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                val location: Location? = task.result
                if (location != null) {
                    // Ubicación obtenida con éxito
                    Log.d("UBICACION", "Lat: ${location.latitude}, Lon: ${location.longitude}")
                    obtenerClima(location.latitude, location.longitude)
                } else {
                    Log.d("UBICACION", "No se pudo obtener la ubicación.")
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun obtenerClima(lat: Double, lon: Double) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)

        service.getCurrentWeather(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weatherResponse ->
                    textViewLatitud.text = "Latitud: ${weatherResponse.latitude}"
                    textViewLongitud.text = "Longitud: ${weatherResponse.longitude}"
                    textViewTemperatura.text = "Temperatura actual: ${weatherResponse.current_weather.temperature}°C"
                    textViewViento.text = "Velocidad del viento: ${weatherResponse.current_weather.windspeed} km/h"
                },
                { error ->
                    Log.e("ERROR", "Error al obtener clima", error)
                }
            )
    }

    // Función para verificar permisos
    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // Función para pedir permisos
    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    // Función que se ejecuta cuando el usuario responde al diálogo de permisos
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Si el permiso fue otorgado, obtenemos la ubicación
                obtenerUbicacion()
            } else {
                // Si el permiso no fue otorgado, puedes mostrar un mensaje al usuario
                Toast.makeText(this, "Se necesita el permiso de ubicación para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}