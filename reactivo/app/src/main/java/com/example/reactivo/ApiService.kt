package com.example.reactivo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun getProducts(): Observable<List<Products>>

    @GET("simple/price")
    fun getCriptomonedas(
        @Query("ids") ids: String,
        @Query("vs_currencies") vs: String = "usd"
    ): Observable<criptomonedas>
}