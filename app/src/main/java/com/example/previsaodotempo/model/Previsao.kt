package com.example.previsaodotempo.model

import java.io.Serializable

data class Previsao(
    var data: String,
    var diaDaSemana: String,
    var maxima: String,
    var minima: String,
    var descricao: String,
    var condicao: String)
//) : Serializable {​​​​​
//
//    companion object {​​​​​
//        private const val serialVersionUID = 1L
//    }​​​​​
//}​​​​​