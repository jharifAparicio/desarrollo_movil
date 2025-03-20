package com.example.testws

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class REST {
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun getRestEngine(): Retrofit {
            val interceptor = HttpLoggingInterceptor() // este paso es opcional
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) // este es el intercector que captura e body
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build() // es es el cliente que hace la peticion

            val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build() // construimos el objeto retrofit
            return retrofit // retornamos el objeto retrofit
        }
    }
}