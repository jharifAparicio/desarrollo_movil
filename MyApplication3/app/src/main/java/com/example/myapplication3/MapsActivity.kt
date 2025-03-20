package com.example.myapplication3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.myapplication3.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.CircleOptions
import kotlin.random.Random

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var latitud: Double = 0.0
    var longitud: Double = 0.0
    var nombre: String = ""
    var descripcion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        latitud = intent.getDoubleExtra("latitud", 0.0)
        longitud = intent.getDoubleExtra("longitud", 0.0)
        nombre = intent.getStringExtra("nombre") ?: ""
        descripcion = intent.getStringExtra("descripcion") ?: ""

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

        val milugar = LatLng(latitud, longitud)
        mMap.addMarker(MarkerOptions().position(milugar).title(nombre).snippet(descripcion))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(milugar))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))

        //marcar un lugar en el mapa
        mMap.addCircle(CircleOptions().center(milugar).radius(100.0).strokeWidth(3f).strokeColor(Color.BLUE).fillColor(Color.TRANSPARENT))

        // generamos 4 lugares aleatorios en el mapa en un rango, con un circulo yle damos un peso a cada uno
        val latMin = -19.025502
        val latMax = -19.058417
        val lonMin = -65.278005
        val lonMax = -65.248483

        val random = Random(System.currentTimeMillis())


        // Generar 4 lugares aleatorios con pesos
        for (i in 0..3) {
            val lat = latMin + (latMax - latMin) * random.nextDouble()
            val lon = lonMin + (lonMax - lonMin) * random.nextDouble()
            // val peso = random.nextDouble() // Peso aleatorio entre 0 y 1

            val lugar = LatLng(lat, lon)
            if(i == 0) {
                mMap.addCircle(CircleOptions().center(lugar).radius(100.0).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.TRANSPARENT))
            }else if(i == 1){
                mMap.addCircle(CircleOptions().center(lugar).radius(100.0).strokeWidth(3f).strokeColor(Color.YELLOW).fillColor(Color.TRANSPARENT))
            }else if(i == 2){
                mMap.addCircle(CircleOptions().center(lugar).radius(100.0).strokeWidth(3f).strokeColor(Color.GREEN).fillColor(Color.TRANSPARENT))
            }else{
                mMap.addCircle(CircleOptions().center(lugar).radius(100.0).strokeWidth(3f).strokeColor(Color.BLUE).fillColor(Color.TRANSPARENT))
            }
        }
    }
}