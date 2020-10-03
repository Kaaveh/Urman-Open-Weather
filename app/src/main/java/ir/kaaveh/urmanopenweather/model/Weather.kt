package ir.kaaveh.urmanopenweather.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_info")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clouds: Int = 0,
    val feelsLike: Double = 0.0,
    val humidity: Int = 0,
    val temp: Double = 0.0,
    val visibility: Int = 0,
    @Embedded(prefix = "weather_") val weather: WeatherInfo = WeatherInfo("", ""),
    val windDeg: Int = 0,
    val windSpeed: Double = 0.0
)