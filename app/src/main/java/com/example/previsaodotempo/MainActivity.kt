package com.example.previsaodotempo

import com.example.previsaodotempo.model.Weather
import WeatherAdapter
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val url: String = "https://api.hgbrasil.com/weather?key=a80da7dd&city_name=Artur%20Nogueira,SP"
    lateinit var queue: RequestQueue

    companion object {
        val TAG = this.javaClass.simpleName
    }
    lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //<editor-fold desc="Gradiente Effect" defaultstate="collapsed">
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val animDrawable = root_layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
        //</editor-fold>

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Aguarde")
        progressDialog.setMessage("Obtendo dados...")
        progressDialog.show()

        queue = Volley.newRequestQueue(this)

        // Requisicao web utilizando Volley
        var requisicaoWeb = StringRequest(Request.Method.GET, url, { resultado ->
            progressDialog.dismiss()
            Log.i(TAG, "RESULTADO: [$resultado]")
            // Criacao de objeto Jaclson para conversao de JSON em POJO, foi necessario incluir a dependencia do modulo Kotlin, a dependencia é implementation "com.fasterxml.jackson.module:jackson-module-kotlin:2.11.3"
            val mapper = ObjectMapper().registerKotlinModule()

            // Criar um node para estrair uma ramificação especifica do JSON recebido
            val node: ObjectNode = mapper.readValue(resultado, ObjectNode::class.java)

            // Selecção de ramificação especifica
            val jsonResult = node.get("results").toString()

            // Conversão para POJO
            val weather = mapper.readValue(jsonResult, Weather::class.java)
            Log.d(TAG, weather.toString())

        }, { erro ->
            Log.e(TAG, "ERRO: [${erro.localizedMessage}]")
        })

        queue.add(requisicaoWeb)

    }

    /**
     * Méto de preenchimento de dados nos widgets com base no objeto Weather
     */
    @SuppressLint("SetTextI18n")
    fun preencherUI(weather: Weather) {
        textViewCidade.text = weather.city_name
        textViewHora.text = weather.time
        textViewData.text = weather.date
        textViewTemperatura.text = "${weather.temp}°"
        textViewMaxima.text = "${weather.forecast[0].max}°"
        textViewMinima.text = "${weather.forecast[0].min}°"
        textViewTempoCelula.text = weather.description
        textViewNascerDoSol.text = weather.sunrise
        textViewPorDoSol.text = weather.sunset
        preencherPrevisoes(weather)
    }

    /**
     * Método de preenchimento da lista de Forcasts (previsoes)
     * Nele cria-se uma isntancia do Adapter e realiza-se a associacao do adapter ao recuclerview
     */
    fun preencherPrevisoes(weather: Weather) {
        adapter = WeatherAdapter(weather.forecast)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }


}
