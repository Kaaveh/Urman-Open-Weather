package ir.kaaveh.urmanopenweather.repository.network

import com.google.gson.annotations.SerializedName
import ir.kaaveh.urmanopenweather.model.Weather
import ir.kaaveh.urmanopenweather.model.WeatherInfo

data class WeatherNetworkDomain(
    val clouds: Int,
    @SerializedName("feels_like") val feelsLike: Double,
    val humidity: Int,
    val temp: Double,
    val visibility: Int,
    val weather: List<WeatherInfo>,
    @SerializedName("wind_deg") val windDeg: Int,
    @SerializedName("wind_speed") val windSpeed: Double
)

fun WeatherNetworkDomain.asDomainModel(): Weather {
    return Weather(
        0,
        this.clouds,
        this.feelsLike,
        this.humidity,
        this.temp,
        this.visibility,
        this.weather[0],
        this.windDeg,
        this.windSpeed
    )
}