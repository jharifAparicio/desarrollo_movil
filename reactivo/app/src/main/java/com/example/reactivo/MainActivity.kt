package com.example.reactivo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    private val disposables = CompositeDisposable()
    private lateinit var buttonBitcoin: Button
    private lateinit var buttonEthereum: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Inicializa los botones y el TextView
        textViewResult = findViewById(R.id.textViewResult)
        buttonBitcoin = findViewById(R.id.buttonBitcoin)
        buttonEthereum = findViewById(R.id.buttonEthereum)

        /*val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.6.29:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory.create())
            .build()*/

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        buttonBitcoin.setOnClickListener {
            observarDatos("bitcoin")
        }

        buttonEthereum.setOnClickListener {
            observarDatos("ethereum")
        }
    }


    private fun observarDatos(crypto: String) {
        val observable = Observable.interval(0, 40, TimeUnit.SECONDS)
            .flatMap { apiService.getCriptomonedas(crypto).toObservable() }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = observable.subscribe({ datos ->
            val mensaje = when (crypto) {
                "bitcoin" -> {
                    val btc = datos.bitcoin.usd
                    "Bitcoin (BTC): $$btc"
                }
                "ethereum" -> {
                    val eth = datos.ethereum.usd
                    "Ethereum (ETH): $$eth"
                }
                else -> "Selecciona una criptomoneda"
            }

            textViewResult.text = mensaje
        }, { error ->
            textViewResult.text = "Error: ${error.message}"
        })

        disposables.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}