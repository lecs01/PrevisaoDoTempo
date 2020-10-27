package com.example.previsaodotempo

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.previsaodotempo.model.Weather
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val url: String = "https://api.hgbrasil.com/weather?key=a80da7dd&city_name=Artur%20Nogueira,SP"
    lateinit var queue: RequestQueue
    companion object {
        val TAG = this.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //<editor-fold desc="Gradiente Effect" defaultstate="collapsed">
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        val animDrawable = root_layout.background as AnimationDrawable
//        animDrawable.setEnterFadeDuration(10)
//        animDrawable.setExitFadeDuration(5000)
//        animDrawable.start()
        //</editor-fold>

        queue = Volley.newRequestQueue(this)

        var requisicaoWeb = StringRequest(Request.Method.GET, url, { resultado ->
            Log.i(TAG, "RESULTADO: [$resultado]")
            val mapper = ObjectMapper();
            val node: ObjectNode = mapper.readValue(resultado, ObjectNode::class.java)
            val jsonResult = node.get("results").toString()

            val weather = mapper.readValue<Weather>(jsonResult, Weather::class.java)

            Log.d(TAG, weather.toString())

        }, { erro ->
            Log.e(TAG, "ERRO: [${erro.localizedMessage}]")
        })

        queue.add(requisicaoWeb)
    }
}