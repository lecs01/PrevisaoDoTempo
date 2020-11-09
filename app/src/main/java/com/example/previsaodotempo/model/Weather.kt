package com.example.previsaodotempo.model

import Forecast
import java.util.*

class Weather {
    var temp: Int = 0
    var date: String = ""
    var time: String = ""
    var condition_code: String = ""
    var description: String = ""
    var currently: String = ""
    var cid: String  = ""
    var city: String  = ""
    var img_id: String = ""
    var humidity: String = ""
    var wind_speedy: String = ""
    var sunrise: String = ""
    var sunset: String = ""
    var condition_slug: String = ""
    var city_name: String = ""
    var forecast: List<Forecast> = Collections.emptyList()
}
