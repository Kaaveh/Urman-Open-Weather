package ir.kaaveh.urmanopenweather.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val currentWeather: Weather,
    val hourlyFutureWeather: List<Weather>
)