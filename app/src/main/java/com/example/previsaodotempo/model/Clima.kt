package com.example.previsaodotempo.model

data class Clima(
    var temperatura: Int,
    var data: String,
    var hora: String,
    var codigoCondicao: String,
    var descricao: String,
    var atualmente: String,
    var cid: String,
    var cidade: String,
    var idImagem: String,
    var humidade: Int,
    var velocidadeDoVento: String,
    var nascerDoSol: String,
    var porDoSol: String,
    var condicaoDoTempo: String,
    var nomeDaCidade: String,
    var previsoes: List<Previsao>
)
