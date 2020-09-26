package ir.kaaveh.urmanopenweather.model


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Weather(
    val clouds: Int,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val temp: Double,
    val visibility: Int,
    @Embedded(prefix = "weather")
    val weather: List<WeatherInfo>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double
)