package com.example.testws

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var comments: Button
    lateinit var Albumns: Button
    lateinit var Usuarios: Button
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        comments = findViewById(R.id.comentarios)
        Albumns = findViewById(R.id.Album)
        Usuarios = findViewById(R.id.Usuarios)
        textView = findViewById(R.id.textView)

        comments.setOnClickListener {
            getDataComments()
        }
        Albumns.setOnClickListener {
            getDataAlbums()
        }
        Usuarios.setOnClickListener {
            getDataUsers()
        }
    }
    private fun getDataAlbums(){
        val apiServices = REST.getRestEngine().create(APIServices::class.java) // creamos el objeto APIServices
        val result: Call<List<AlbumData>> = apiServices.getAlbums() // ejecutamos la peticion, usamos directamente el getAlbums

        result.enqueue(object : retrofit2.Callback<List<AlbumData>> {
            // metodos de una clase embedida
            override fun onResponse(p0: Call<List<AlbumData>>, p1: Response<List<AlbumData>>) {
                if(p1.isSuccessful){
                    Log.d("MainActivity", p1.body().toString())
                }else{
                    Log.d("MainActivity", "Ocurrio un Error")
                }
                textView.text = p1.body().toString()
            }

            override fun onFailure(p0: Call<List<AlbumData>>, p1: Throwable) {
                Log.d("MainActivity", "Ocurrio un Error" + p1.message)
            }

        })
    }

    private fun getDataComments(){
        val apiServices = REST.getRestEngine().create(APIServices::class.java) // creamos el objeto APIServices
        val result: Call<List<ComentsData>> = apiServices.getComments() // ejecutamos la peticion, usamos directamente el getAlbums
        result.enqueue(object : retrofit2.Callback<List<ComentsData>> {
            // metodos de una clase embedida
            override fun onResponse(p0: Call<List<ComentsData>>, p1: Response<List<ComentsData>>) {
                if(p1.isSuccessful){
                    Log.d("MainActivity", p1.body().toString())
                }else{
                    Log.d("MainActivity", "Ocurrio un Error")
                }
                textView.text = p1.body().toString()
            }

            override fun onFailure(p0: Call<List<ComentsData>>, p1: Throwable) {
                Log.d("MainActivity", "Error al conectarse" + p1.message)
            }
        })
    }

    private fun getDataUsers(){
        val apiServices = REST.getRestEngine().create(APIServices::class.java) // creamos el objeto APIServices
        val result: Call<List<UsersData>> = apiServices.getUsers() // ejecutamos la peticion, usamos directamente el getAlbums
        result.enqueue(object : retrofit2.Callback<List<UsersData>> {
            override fun onResponse(p0: Call<List<UsersData>>, p1: Response<List<UsersData>>) {
                if(p1.isSuccessful){
                    Log.d("MainActivity", p1.body().toString())
                }else{
                    Log.d("MainActivity", "Ocurrio un Error")
                }
                textView.text = p1.body().toString()
            }

            override fun onFailure(p0: Call<List<UsersData>>, p1: Throwable) {
                Log.d("MainActivity", "Error al conectarse" + p1.message)
            }
        })
    }
}