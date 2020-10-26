package com.example.previsaodotempo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.ref.ReferenceQueue
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val PERMISSION_ID = 42
    var lat: Double = 0.0
    var long: Double = 0.0
    var listaPrevisoes = ArrayList<Previsao>()
    lateinit var dialog: ProgressDialog
    lateinit var url: String

    lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Gradient Effect

                val dialog: ProgressDialog(this)
        dialog.setTitle("Aguarde...")
        dialog.setMessage("Obtendo dados da HGBrasil...")
        dialog.show()

        queue = Volley.newRequestQueue(this)

        val urlRequest = "https://api.hgbrasil.com/weather?key=a80da7dd&city_name=Artur%20Nogueira,SP"

        val request = StringRequest(Request.Method.GET, urlRequest, { result ->
            Log.i("RESPONSE: ", result.toString())
            dialog.dismiss()
        }, {error ->
            Log.e("ERROR: ", error!!.localizedMessage)
        })

        queue.add(request)

    }
}