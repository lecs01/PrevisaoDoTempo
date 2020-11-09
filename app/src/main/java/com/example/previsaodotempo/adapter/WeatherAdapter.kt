import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempo.R

class WeatherAdapter(private val weather: List<Forecast>): RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.previsao_cell, parent, false)
        return WeatherViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherItem = weather[position]
        holder.textViewTime?.text = weatherItem.description
        holder.textViewMinMax?.text = "${weatherItem.weekday} ${weatherItem.date} " +
                "- Máxima: ${weatherItem.max}° - Mínima: ${weatherItem.min}°"
        when(weatherItem.condition){
            "storm" -> holder.image?.setImageResource(R.drawable.storm)
            "snow" -> holder.image?.setImageResource(R.drawable.snow)
            "rain" -> holder.image?.setImageResource(R.drawable.rain)
            "fog" -> holder.image?.setImageResource(R.drawable.fog)
            "clear_day" -> holder.image?.setImageResource(R.drawable.sun)
            "clear_night" -> holder.image?.setImageResource(R.drawable.moon)
            "cloud" -> holder.image?.setImageResource(R.drawable.cloudy)
            "cloudly_day" -> holder.image?.setImageResource(R.drawable.cloud_day)
            "cloudly_night" -> holder.image?.setImageResource(R.drawable.cloudy_night)
        }
    }

    override fun getItemCount(): Int {
        return weather.size
    }
}

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView? = itemView.findViewById(R.id.imageViewIconWeather)
    var textViewTime: TextView? = itemView.findViewById(R.id.textViewTempoCelula)
    var textViewMinMax: TextView? = itemView.findViewById(R.id.textViewMaxMinCelula)
}
